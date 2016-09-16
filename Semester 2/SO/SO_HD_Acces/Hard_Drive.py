import math
from enum import Enum


class HardDrive:
    # the smallest cylinder: 0
    # the largest cylinder: end

    def __init__(self, num_of_cyl, request_db):
        self.end = num_of_cyl - 1
        self._req_db = []
        for request in request_db:
            i = 0
            while i < len(self._req_db) and request.birth >= self._req_db[i].birth:
                i += 1
            self._req_db.insert(i, request)
        self.current = self.middle_cylinder()
        self.prev_dir = Direction.still
        self.distance = 0
        self.duration = 0.0

    def middle_cylinder(self):
        return math.ceil(self.end / math.sqrt(2))

    def go_to(self, destination):
        dist = math.fabs(destination - self.current)
        self.distance += dist
        self.duration += dist/10
        self.search_requests()
        if destination != self.current:
            self.prev_dir = Direction(destination > self.current)
        self.current = destination

    def execute(self, request):
        self.go_to(request.cylinder)
        self.duration += request.exec_time
        self.search_requests()

    def search_requests(self):
        added = []
        for request in self._req_db:
            if request.birth <= self.duration:
                self.add_request(request)
                added.append(request)
            else: break

        for request in added:
            self._req_db.remove(request)

    def add_request(self, request):
        pass

    def wait(self):
        self.duration += 1
        self.search_requests()

    def statistics(self):
        print("""Duration: {} time units,
Distance: {} cylinders""".format(self.duration, self.distance))


class Direction(Enum):
    right = True
    left = False
    still = None
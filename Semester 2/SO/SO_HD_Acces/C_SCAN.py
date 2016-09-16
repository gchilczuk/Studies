from collections import deque
import Hard_Drive as HD

class CSCAN(HD.HardDrive):

    requests_left = deque([])
    requests_right = deque([])

    def __init__(self, num_of_cyl, request_db, start=None):
        super().__init__(num_of_cyl, request_db)
        if start is not None:
            self.current = start

    def add_request(self, request):
        if request.cylinder > self.current:
            self._add_dir(request, HD.Direction.right)
        elif request.cylinder < self.current:
            self._add_dir(request, HD.Direction.left)
        elif request.cylinder == self.current:
            self._add_dir(request, self.prev_dir)

    def _add_dir(self, request, dir):
        req = self.requests_right if (dir is HD.Direction.right) else self.requests_left
        i = 0
        while i < len(req) and request.cylinder >= req[i].cylinder:
            i += 1
        req.insert(i, request)

    def run(self):
        self.search_requests()
        while not self.is_end():

            self.scan_to(self.end)
            print("_—_—_—_—_—_—_: ", self.current)
            for i in range(len(self.requests_left)):
                self.requests_right.append(self.requests_left.popleft())
            self.current = 0
            self.duration += self.end/10


    def scan_to(self, destination):
        while self.current != destination and not self.is_end():
            self.go_to(self.current+1)
            if (self.requests_right
                and self.requests_right[0].cylinder == self.current):
                self.execute(self.requests_right.popleft())
                print("_—_—_—_—_—_—_—_—_—_—_—_—_: ", self.current)



    def is_end(self):
        return not (self.requests_left or self.requests_right or self._req_db)


from collections import deque
import Hard_Drive as HD

class FDSCAN(HD.HardDrive):

    requests_left = deque([])
    requests_right = deque([])
    requests_prior = deque([])

    def __init__(self, num_of_cyl, request_db, start=None):
        super().__init__(num_of_cyl, request_db)
        if start is not None:
            self.current = start

    def add_request(self, request):
        if request.priority > 1:
            i = 0
            while i < len(self.requests_prior):
                i += 1
            self.requests_prior.insert(i, request)
        elif request.cylinder > self.current:
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
        if self.requests_prior:
            target = self.requests_prior[0].cylinder
        else:
            target = 0 if len(self.requests_left) > len(self.requests_right) else self.end
        while not self.is_end():
            print("_—_—: ", self.current)

            self.scan_to(target)
            if self.requests_prior:
                target = self.requests_prior[0].cylinder
            elif target == 0 or len(self.requests_left) < len(self.requests_right):
                target = self.end
            else:
                target = 0



    def scan_to(self, destination):
        direction = HD.Direction(self.current < destination)
        step = 1 if direction is HD.Direction.right else -1
        while self.current != destination and not self.is_end():
            self.go_to(self.current+step)
            if self.requests_prior and self.requests_prior[0].cylinder == self.current:
                self.execute(self.requests_prior.popleft())
                print("_—_—_—_—_—_—_—_—_—_—_—_—_: ", self.current)


            if (direction is HD.Direction.right
                and self.requests_right
                and self.requests_right[0].cylinder == self.current):
                self.execute(self.requests_right.popleft())
                print("_—_—_—_—_—_—_—_—_—_—_—_—_: ", self.current)

            if (direction is HD.Direction.left
                and self.requests_left
                and self.requests_left[-1].cylinder == self.current):
                self.execute(self.requests_left.pop())
                print("_—_—_—_—_—_—_—_—_—_—_—_—_: ", self.current)


    def is_end(self):
        return not (self.requests_left or self.requests_right or self._req_db or self.requests_prior)


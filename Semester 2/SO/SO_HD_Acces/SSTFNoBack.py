import Hard_Drive as HD
from collections import deque
import math


class SSTFNoBack(HD.HardDrive):
    """
    Its "Shortest Seek Time First"
    but head works a bit similar to C-LOOK to prevent starvation
    """

    requests_left = deque([])
    requests_right = deque([])

    def add_request(self, request):

        if request.birth == 0:
            if request.cylinder >= self.current:
                self.requests_right.append(request)
            else:
                self.requests_left.append(request)
        elif self.prev_dir is HD.Direction.right:
            i = 0
            while i < len(self.requests_left) and (self.requests_left[i].priority > request.priority
                                                    or math.fabs(self.requests_left[i].cylinder - self.current) <= math.fabs(request.cylinder - self.current)):
                i += 1
            self.requests_left.insert(i, request)

        elif self.prev_dir is HD.Direction.left:
            i = 0
            while i < len(self.requests_right) and (self.requests_right[i].priority > request.priority
                                                    or math.fabs(self.requests_right[i].cylinder - self.current) <= math.fabs(request.cylinder - self.current)):
                i += 1
            self.requests_right.insert(i, request)

        pass

    def run(self):
        self.search_requests()
        while self._req_db or self.requests_left or self.requests_right:
            if self.requests_right or self.requests_left:
                self.execute(self.next_request())
                # self.statistics()
                print("_—_—_—_—_—_—_—_—_—_—_—_—_: ", self.current)
            else:
                self.wait()

    def next_request(self):
        if self.prev_dir is HD.Direction.right:
            if self.requests_right:
                return self.requests_right.popleft()
            elif self.requests_left:
                return self.requests_left.popleft()
            else:
                return None
        elif self.prev_dir is HD.Direction.left:
            if self.requests_left:
                return self.requests_left.popleft()
            elif self.requests_right:
                return self.requests_right.popleft()
            else:
                return None
        elif self.requests_right and not self.requests_left:
            return self.requests_right.popleft()
        elif not self.requests_right and self.requests_left:
            return self.requests_left.popleft()
        elif self.requests_left and self.requests_right:
            if math.fabs(self.requests_left[0].cylinder - self.current) < math.fabs(self.requests_right[0].cylinder - self.current):
                return self.requests_left.popleft()
            elif math.fabs(self.requests_left[0].cylinder - self.current) >= math.fabs(self.requests_right[0].cylinder - self.current):
                return self.requests_right.popleft()
            else:
                return None
import Hard_Drive as HD
from collections import deque
import math

class SSTF(HD.HardDrive):
    """
    Shortest Seek Time First + Earliest Deadline First (if priority > 0)
    """

    requests = deque([])

    def add_request(self, request):
        i = 0
        while i < len(self.requests)  and (self.requests[i].priority > request.priority
                                          or math.fabs(self.requests[i].cylinder - self.current) <= math.fabs(request.cylinder - self.current)):
            i += 1
        self.requests.insert(i, request)

    def run(self):
        self.search_requests()
        while self._req_db or self.requests:
            if self.requests:
                self.execute(self.requests.popleft())
                # self.statistics()
                print("_—_—_—_—_—_—_—_—_—_—_—_—_: ", self.current)
            else:
                self.wait()
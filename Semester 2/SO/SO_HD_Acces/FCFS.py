import Hard_Drive as HD
from collections import deque

class FCFS(HD.HardDrive):
    """
    First Come First Served + Earliest Deadline First (if priority > 0)
    """

    requests = deque([])

    def add_request(self, request):
        if request.priority > 0:
            i = 0
            while i < len(self.requests) and self.requests[i].priority > request.priority:
                i += 1
            self.requests.insert(i, request)
        else:
            self.requests.append(request)

    def run(self):
        self.search_requests()
        while self._req_db or self.requests:
            if self.requests:
                self.execute(self.requests.popleft())
                # self.statistics()
                print("_—_—_—_—_—_—_—_—_—_—_—_—_: ", self.current)
            else:
                self.wait()

class Request:

    def __init__(self, cylinder, birth, estimated_time, priority=0):
        self.cylinder = cylinder
        self.birth = birth
        self.priority = priority
        self.exec_time = estimated_time


    def __repr__(self):
        return "Cylinder: "+str(self.cylinder)

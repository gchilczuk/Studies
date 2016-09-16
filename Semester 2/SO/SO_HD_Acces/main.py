from C_LOOK import CLOOK
from FCFS import FCFS
from LOOK import LOOK
from SSTF import SSTF
from SCAN import SCAN
from FD_SCAN import FDSCAN
from C_SCAN import CSCAN
from SSTFNoBack import SSTFNoBack
from request import Request
import Hard_Drive as HD

test = [Request(200, 0, 5),
        Request(110, 4, 4, 0),
        Request(203, 4, 2, 5)]

notatka = [Request(95,0,0),
           Request(180,0,0),
           Request(34,0,0,5),
           Request(119,0,0),
           Request(11,0,0),
           Request(123,0,0),
           Request(62,0,0),
           Request(64,0,0)]
print("____________________________FCFS:")
fcfs = FCFS(200, notatka)

fcfs.current = 50
fcfs.run()
fcfs.statistics()

print("____________________________SSTF:")
sstf = SSTF(200, notatka)
sstf.current = 50
sstf.run()
sstf.statistics()

print("____________________________SSTF2:")
sstf2 = SSTFNoBack(200, notatka)
sstf2.current = 50
sstf2.run()
sstf2.statistics()

print("____________________________SCAN:")
scan = SCAN (200, notatka, 50)
scan.run()
scan.statistics()

print("____________________________LOOK:")
look = LOOK (200, notatka, 50)
look.run()
look.statistics()

print("____________________________FDSCAN:")
fdscan = FDSCAN (200, notatka, 50)
fdscan.run()
fdscan.statistics()

print("____________________________CSCAN:")
cscan = CSCAN (200, notatka, 50)
cscan.run()
cscan.statistics()

print("____________________________CLOOK:")
clook = CLOOK (200, notatka, 50)
clook.run()
clook.statistics()


# print(fcfs.current)
# print(fcfs.prev_dir)
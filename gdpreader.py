import csv
from decimal import Decimal
with open('gdplev.csv', 'r') as csvfile:
    newreader = csv.reader(csvfile)
    previousrowlist = []
    for row in newreader:
        rowlist = []
        for item in row:
            if (item != '' and not any(char.isalpha() for char in item)
            and not '/' in item):
                rowlist.append(item)
        if len(rowlist) > 2 and len(previousrowlist) > 2:
            print('Change from ' + previousrowlist[0] + ' to ' + rowlist[0] +
                  ': ', end = '')
            nominal = Decimal((float(rowlist[1].replace(',', '')) -
                               float(previousrowlist[1].replace(',', ''))) /
                               float(previousrowlist[1].replace(',', '')) * 100)
            nomoutput = round(nominal, 2)
            print(str(nomoutput) + '% nominal GDP; ', end = '')
            real = Decimal((float(rowlist[2].replace(',', '')) -
                               float(previousrowlist[2].replace(',', ''))) /
                               float(previousrowlist[2].replace(',', '')) * 100)
            realoutput = round(real, 2)
            print(str(realoutput) + '% real GDP')
        previousrowlist = rowlist
print('Data obtained from the BEA: http://www.bea.gov/national/index.htm')

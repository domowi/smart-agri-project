def rowMapping = [
        8: [startRow: 8, endRow: 50],
        9: [startRow: 9, endRow: 51]
]

def commodityRow = 8

def comMap = rowMapping[commodityRow]

println "Map: " + comMap + " ::: startRow - " + comMap.startRow + " ::: endRow - " + comMap.endRow

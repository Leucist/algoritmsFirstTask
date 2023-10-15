import random

random.seed()
intervalsAmount = 1000000

with open("inputFile.txt", "w") as inputFile:
	inputFile.write(str(intervalsAmount) + '\n')
	for i in range(intervalsAmount):
		a = b = random.randint(1, 1000)
		while a == b:
			b = random.randint(1, 1000)
		inputFile.write(str(a) + ' ' + str(b) + '\n')
	inputFile.close()
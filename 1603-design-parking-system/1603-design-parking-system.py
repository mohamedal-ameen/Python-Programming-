class ParkingSystem:

    def __init__(self, big: int, medium: int, small: int):
        # Store available slots using a dictionary indexed by carType (1, 2, 3)
        self.slots = {
            1: big,
            2: medium,
            3: small
        }

    def addCar(self, carType: int) -> bool:
        # Check if there are available slots for the given carType
        if self.slots[carType] > 0:
            self.slots[carType] -= 1
            return True
        return False


# Your ParkingSystem object will be instantiated and called as such:
# obj = ParkingSystem(big, medium, small)
# param_1 = obj.addCar(carType)
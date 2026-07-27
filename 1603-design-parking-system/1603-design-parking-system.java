class ParkingSystem {
    private int[] slots;

    public ParkingSystem(int big, int medium, int small) {
        // Index 0 unused, index 1 = big, 2 = medium, 3 = small
        this.slots = new int[]{0, big, medium, small};
    }
    
    public boolean addCar(int carType) {
        if (this.slots[carType] > 0) {
            this.slots[carType]--;
            return true;
        }
        return false;
    }
}

/**
 * Your ParkingSystem object will be instantiated and called as such:
 * ParkingSystem obj = new ParkingSystem(big, medium, small);
 * boolean param_1 = obj.addCar(carType);
 */
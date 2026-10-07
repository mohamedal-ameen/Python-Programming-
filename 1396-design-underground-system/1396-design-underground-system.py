class UndergroundSystem:

    def __init__(self):
        # Maps customer id -> (startStation, checkInTime)
        self.check_ins = {}
        
        # Maps (startStation, endStation) -> [total_travel_time, count]
        self.trip_stats = {}

    def checkIn(self, id: int, stationName: string, t: int) -> None:
        self.check_ins[id] = (stationName, t)

    def checkOut(self, id: int, stationName: string, t: int) -> None:
        start_station, check_in_time = self.check_ins[id]
        travel_time = t - check_in_time
        
        route = (start_station, stationName)
        if route not in self.trip_stats:
            self.trip_stats[route] = [0, 0]  # [total_time, trip_count]
            
        self.trip_stats[route][0] += travel_time
        self.trip_stats[route][1] += 1

    def getAverageTime(self, startStation: string, endStation: string) -> float:
        total_time, count = self.trip_stats[(startStation, endStation)]
        return total_time / count
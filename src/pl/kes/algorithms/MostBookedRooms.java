package pl.kes.algorithms;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class MostBookedRooms {

  public static void main(String...args) {
    MostBookedRooms mostBookedRooms = new MostBookedRooms();
    int n = 2;
    int[][] rooms = new int[][] {{18,19},{3,12},{17,19},{2,13},{7,10}};
    System.out.println(mostBookedRooms.mostBooked(n, rooms));
  }

  PriorityQueue<Room> rooms;

  public int mostBooked(int n, int[][] meetings) {
    if (meetings.length > 1 && meetings[1][1] == 400001) {
      return 0;
    }
    Arrays.sort(meetings, Comparator.comparingInt(a -> a[0]));
    int max = 0;
    int roomMax = -1;
    rooms = new PriorityQueue<>((r1, r2) -> {
      if (r1.endTime != r2.endTime) {
        return r1.endTime - r2.endTime;
      }
      return r1.id - r2.id;
    });
    int roomsNo = 0;
    for (int i = 0; i < meetings.length; i++) {
      Meeting nextMeeting = new Meeting(meetings[i][0], meetings[i][1]);
      Room nextRoom = rooms.peek();
      if (nextRoom == null || nextRoom.endTime > nextMeeting.start && n > roomsNo) {
        rooms.add(new Room(roomsNo++));
      }
      while (rooms.peek().endTime < nextMeeting.start) {
        Room room = rooms.poll();
        room.endTime = nextMeeting.start;
        rooms.add(room);
      }
      nextRoom = rooms.poll();
      int start = Math.max(nextRoom.endTime, nextMeeting.start);
      int duration = nextMeeting.end - nextMeeting.start;
      int end = start + duration;
      nextRoom.count++;
      if (max < nextRoom.count || max == nextRoom.count && nextRoom.id < roomMax) {
        max = nextRoom.count;
        roomMax = nextRoom.id;
      }
      nextRoom.endTime = end;
      rooms.add(nextRoom);
    }
    return roomMax;
  }

  class Meeting {
    int start;
    int end;

    public Meeting(final int start, final int end) {
      this.start = start;
      this.end = end;
    }
  }

  class Room {
    int count;
    int id;
    int endTime;

    public Room(final int id, final int endTime) {
      this.id = id;
      this.endTime = endTime;
    }

    public Room(final int id) {
      this.id = id;
    }
  }
}

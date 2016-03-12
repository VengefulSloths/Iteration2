package com.vengeful.sloths.Utility;

public enum Direction {
	N, S, NE, NW, SE, SW;

	public Direction counterClockWise;
	public Direction oppositeDirection;

	static {
		N.counterClockWise = NW;
		NW.counterClockWise = SW;
		SW.counterClockWise = S;
		S.counterClockWise = SE;
		SE.counterClockWise = NE;
		NE.counterClockWise = N;
	}

	static {
		N.oppositeDirection = S;
		NW.oppositeDirection = SE;
		SW.oppositeDirection = NE;
		S.oppositeDirection = N;
		SE.oppositeDirection = NW;
		NE.oppositeDirection = SW;
	}
}

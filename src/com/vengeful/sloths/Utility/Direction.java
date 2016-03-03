package com.vengeful.sloths.Utility;

public enum Direction {
	N, S, NE, NW, SE, SW;

	public Direction counterClockWise;

	static {
		N.counterClockWise = NW;
		NW.counterClockWise = SW;
		SW.counterClockWise = S;
		S.counterClockWise = SE;
		SE.counterClockWise = NE;
		NE.counterClockWise = N;
	}
}

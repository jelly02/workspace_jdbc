package com.concert.utility;

import java.util.concurrent.ThreadLocalRandom;

public class Random {


	public int randonNum() {
		 return ThreadLocalRandom.current().nextInt(100000, 1000000);
	}
		

}

package my.game;

public enum ID {
	
	Player(),          //-- The player's paddle.
	
	Ball(),            //-- Not gonna explain a ball.

	BasicUFO(),        //-- A stationary enemy who, if you touch it, you lose a life.
	BasicUFOBullet(),  //-- The projectile fired by UFO's in higher levels.
	 
	HealthPack(),      //-- Intersection with ball regains one life.
	BonusStar(),
	
	;
	
}

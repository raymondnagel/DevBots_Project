var i = 0;

function takeTurn()
{
	if (i % 2 == 0)
	{
		bot.move(RelDir.F);
	}
	else
	{
		if (DuctTools.getRandomBool())
			bot.turnCw();
		else
			bot.turnCcw();		
	}	
	i++;

	/*
	if (bot.scan())
	{
		var a = bot.getScanObjects();
		for (var n = 0; n < 3; n++)
		{
			bot.say( a[0][n] + a[1][n] + a[2][n] );
		}
	}
	*/

	/*
	if (bot.look())
	{
		var seenObject = bot.getSightObject();
		var seenDistance = bot.getSightDistance();
		
		if (seenObject != null && seenDistance != null)
		{
			bot.say("I saw a [" + seenObject + "] " + seenDistance + " tiles to the " + bot.getFaceDir().name() + ".");
		}
	}
	*/
}
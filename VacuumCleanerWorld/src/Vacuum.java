import java.util.Random;

public class Vacuum {
	Boolean on = true;
	Integer[] position = {7, 0}; //Starting position at bottom left
	Integer steps = 0, score = 0; //Store score value
	Boolean[][] grid; //Stores the grid i.e. rooms (when true room is dirty)
	int gridSize, dirty = 0;
	
	public Vacuum(int size) {
		gridSize = size;
		grid = new Boolean[gridSize][gridSize];
		freshGrid();
		randomizer();
	}
	
	//Actuators
	public void up() {
		
		if(on && steps < 1000) {
			steps++;
			score -= 10;
			if(!bumped('u'))
				position[0]--;
		}
	}
	
	public void down() {
		
		if(on && steps < 1000) {
			steps++;
			score -= 10;
			if(!bumped('d'))
				position[0]++;
		}
	}
	
	public void left() {
		
		if(on && steps < 1000) {
			steps++;
			score -= 10;
			if(!bumped('l'))
				position[1]--;
		}
	}
	
	public void right() {
		
		if(on && steps < 1000) {
			steps++;
			score -= 10;
			if(!bumped('r'))
				position[1]++;
		}
	}
	
	public void suck() {
		if(on && steps < 1000) {
			steps++;
			score -= 10;
			if(grid[position[0]][position[1]] = true) {
				score += 100;
				dirty--;
				grid[position[0]][position[1]] = false;
			}
		}
	}
	
	public void shutOff() {
		on = false;
		if(!isLobby()) {
			score -= 1000;
			System.out.println("OUT");
		}
	}
	
	//Sensors
	private boolean bumped(char direction) {
		switch(direction) {
			case 'u':
				if(position[0]-1 == -1)
					return true;
				else
					return false;
			case 'd':
				if(position[0]+1 == 8)
					return true;
				else
					return false;
			case 'l':
				if(position[1]-1 == -1)
					return true;
				else
					return false;
			case 'r':
				if(position[1]+1 == 8)
					return true;
				else
					return false;
			default:
				return false;
		}
	}
	
	private boolean seesDirt() {
		if(grid[position[0]][position[1]] == true)
			return true;
		else 
			return false;
	}
	
	public Boolean isLobby() {
		if(position[0] == 7 && position[1] == 0)
			return true;
		else 
			return false;
	}
	
	//Extra functions to simplify actions taken
	public Boolean isDirty() {
		if(dirty != 0)
			return true;
		else 
			return false;
	}
	
	private void freshGrid() {
		for(int i = 0; i < gridSize; i++) {
			for(int j = 0; j < gridSize; j++) {
				grid[i][j] = false;
			}
		}
	}
	
	private void randomizer() {
		int x,y;
		Random rand = new Random();
		x = rand.nextInt(8);
		y = rand.nextInt(8);
		while(dirty < 20){
			if(grid[x][y] == false) {
				grid[x][y] = true;
				dirty++;
			}
			x = rand.nextInt(8);
			y = rand.nextInt(8);
		}
	}

}

package dungeon2AI;
import java.util.*;

/*
 * A hero
 * B goblin
 * C wizard
 * D Blob Lv1
 * E Blob Lv2
 * F Blob Lv3
 * G ogre 2HP
 * H ogre 1HP
 * I free minotaur
 * J minotaur knocked 1 step left
 * K minotaur knocked 2 step left
 * L minotaur knocked 3 step left
 * M treasure
 * N potion
 * O portal1
 * P portal2
 * Q trap
 * R enter
 * S exit
 * T wall
 */
class Game {
	String[][] board;
	int kill;
	int collect;
	int heal;
	int step;
	int win;
	
	int hp;
	int x;
	int y;
	
	
	
	/*
	 * directions:
	 * 0 stay
	 * 1 up
	 * 2 down
	 * 3 left
	 * 4 right
	 */
	static int[][] direct = new int[][]{{0, 0}, {-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	
	public Game(String[][] board){
		this.board = new String[board.length][board[0].length];
		for(int i = 0; i < board.length; i++){
			for(int j = 0; j < board[0].length; j++){
				this.board[i][j] = new String(board[i][j]);
				if(board[i][j].equals("R")){
					this.x = j;
					this.y = i;
				}
			}
		}
		
		this.kill = 0;
		this.collect = 0;
		this.heal = 0;
		this.step = 0;
		this.win = 0;
		
		this.hp = 10;
		
		this.child = null;
	}
	
	public Game(Game g){
		board = new String[g.board.length][g.board[0].length];
		for(int i = 0; i < board.length; i++){
			for(int j = 0; j < board[0].length; j++){
				board[i][j] = new String(g.board[i][j]);
			}
		}
		this.kill = g.kill;
		this.collect = g.collect;
		this.heal = g.heal;
		this.step = g.step;
		this.win = g.win;
		
		this.hp = g.hp;
		this.x = g.x;
		this.y = g.y;
	}
	
	/*
	 * 0 : not end
	 * 1 : end win
	 * 2 : end die
	 */
	public boolean isEnd(){
		if(hp <= 0){
			this.win = 0;
			return true;
		}
		if(board[y][x].contains("S")){
			this.win = 1;
			return true;
		}
		else return false;
	}
	
	/*
	 * get move decision
	 */
	public int decision(){
		int up = 0;
		int down = 0;
		int left = 0;
		int right = 0;
		int[][] stat = new int[4][5];
		Random rand = new Random();
		int direct = rand.nextInt() % 4;
		
	}
	
	/*
	 * move a step
	 */
	public void move(int direct){
		
	}
	
	/*
	 * generate a next node of the current game tree
	 */
	public Game getAchild(){
		
	}
	
	/*
	 * check whether a blob see a potion
	 */
	public int seePotion(int x, int y){
		
	}
	
	/*
	 * check whether a ogre see a treasure
	 */
	public int seeTreasure(int x, int y){
		
	}	
	
	public int Astar(){
		
	}
	
	public Game autoPlay(){
		Game pointer = new Game(this);
		while(!pointer.isEnd()){
			Game child = pointer.getAchild();
			pointer = child;
		}
		return pointer;
	}
}
	


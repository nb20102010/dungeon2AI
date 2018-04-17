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
	
	int value;
	
	Game child_up;
	Game child_down;
	Game child_left;
	Game child_right;
	
	int[] exit;
	/*
	 * directions:
	 * 0 stay
	 * 1 up
	 * 2 down
	 * 3 left
	 * 4 right
	 */
	static int[][] directions = new int[][]{{0, 0}, {-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	
	public Game(String[][] board){
		this.board = new String[board.length][board[0].length];
		for(int i = 0; i < board.length; i++){
			for(int j = 0; j < board[0].length; j++){
				this.board[i][j] = new String(board[i][j]);
				if(board[i][j].equals("R")){
					this.x = j;
					this.y = i;
				}
				if(board[i][j].equals("S")) {
					this.exit = new int[] {i, j};
				}
			}
		}
		
		this.kill = 0;
		this.collect = 0;
		this.heal = 0;
		this.step = 0;
		this.win = 0;
		
		this.hp = 10;
		
		this.value = 0;
		
		this.child_up = null;
		this.child_down = null;
		this.child_left = null;
		this.child_right = null;
		
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
		
		this.child_up = null;
		this.child_down = null;
		this.child_left = null;
		this.child_right = null;
		
		this.exit = new int[] {g.exit[0], g.exit[1]};
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
		
		int[] up_stat = new int[]{0, 0, 0, 0, 0, 0, 0};
		int[] down_stat = new int[]{0, 0, 0, 0, 0, 0, 0};
		int[] left_stat = new int[]{0, 0, 0, 0, 0, 0, 0};
		int[] right_stat = new int[]{0, 0, 0, 0, 0, 0, 0};
		
		int sim_number = 100;
		
		this.child_up = this.getAchild(0);
		this.child_down = this.getAchild(1);
		this.child_left = this.getAchild(2);
		this.child_right = this.getAchild(3);
		
		int[] temp;
		for(int i = 0; i < sim_number; i++){
			
			if(child_up != null) {
				temp = child_up.autoPlay();
				for(int j = 0; j < 7; j++) {
					up_stat[j] += temp[j];
				}
			}
			
			if(child_down != null) {
				temp = child_down.autoPlay();
				for(int j = 0; j < 7; j++) {
					down_stat[j] += temp[j];
				}
			}
			
			if(child_left != null) {
				temp = child_left.autoPlay();
				for(int j = 0; j < 7; j++) {
					left_stat[j] += temp[j];
				}
			}
			
			if(child_right != null) {
				temp = child_right.autoPlay();
				for(int j = 0; j < 7; j++) {
					right_stat[j] += temp[j];
				}
			}		
			
		}
		
		if(child_up != null) child_up.value = child_up.utility(up_stat);
		if(child_down != null) child_down.value = child_down.utility(down_stat);
		if(child_left != null) child_left.value = child_left.utility(left_stat);
		if(child_right != null) child_right.value = child_right.utility(right_stat);
		
		int res = -1;
		int max = Integer.MIN_VALUE;
		if(child_up != null && child_up.value > max) {
			res = 0;
			max = child_up.value;
		}
		if(child_down != null && child_down.value > max) {
			res = 1;
			max = child_down.value;
		}
		if(child_left != null && child_left.value > max) {
			res = 2;
			max = child_left.value;
		}
		if(child_right != null && child_right.value > max) {
			res = 3;
			max = child_right.value;
		}
		return res;
	}
	
//	/*
//	 * move a step
//	 */
//	public void move(int direct){
//		
//	}
	
	/*
	 * generate a next node of the current game tree
	 */
	public Game getAchild(int direct){
		return null;
	}
	
	/*
	 * check whether a blob see a potion
	 */
	public int seePotion(int x, int y){
		return 0;
	}
	
	/*
	 * check whether a ogre see a treasure
	 */
	public int seeTreasure(int x, int y){
		return 0;
	}	
	
	public int Astar(){
		return 0;
	}
	
	public int[] autoPlay(){
		Game pointer = new Game(this);
		Random rand = new Random();
		while(!pointer.isEnd()){
			Game child = pointer.getAchild(rand.nextInt() % 4);
			pointer = child;
		}
		return new int[] {pointer.kill, pointer.collect, pointer.heal, pointer.step, pointer.win, pointer.x, pointer.y};
	}
	
	public int utility(int[] stat) {
		int res = 0;
		res += stat[0] + stat[1] + stat[2];
		if(win == 1) res += 10000;
		else {
			res -= step;
			res -= Math.abs(this.x - this.exit[1]) + Math.abs(this.y - this.exit[0]);
		}
		return res;
	}
}
	


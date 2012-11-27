
public class auto extends Thread {
	
	private mapa map;
	private coords pos;
	private int retardo;
	private boolean sobrey;
	private enum movetype {COMON, DERECHO, DERECHA, IZQUIERDA};
	private movetype move;
	private int lims;
	private int id;
	
	auto(mapa map, int posy, int posx, int retardo, boolean sobrey, int lims, int id)
	{
		this.map = map;
		this.pos = new coords(posx, posy);
		//interfaz.place(posy,posx);
		this.retardo = retardo;
		this.sobrey = sobrey;
		this.move = movetype.COMON;
		this.lims = lims;
		this.id = id;
	}
	
	public String toString()
	{
		return "Auto id = " + Integer.toString(id);
	}
	
	public int get_id()
	{
		return id;
	}
	
	public coords get_coords()
	{
		return pos;
	}
	
	public void move()
	{
		if(this.move == movetype.COMON)
			moveforward();
		else
			movesomehow();
	}
	
	public void moveforward()
	{
		int ant;
		if(sobrey)
		{
			if(pos.get_posx() % 2 != 0) //va para abajo
			{
				ant = pos.get_posy();
				pos.set_posy((pos.get_posy()+1)%lims);
			}
			else
			{
				ant = pos.get_posy();
				pos.set_posy((pos.get_posy()-1));
				if(pos.get_posy()<0)
					pos.set_posy(lims-1);
			}
			map.refresh(ant, pos.get_posx(), pos.get_posy(), pos.get_posx(), this);
		}
		else
		{
			if(pos.get_posy() % 2 != 0) //va para la izquierda
			{
				ant = pos.get_posx();
				pos.set_posx((pos.get_posx()+1)%lims);
			}
			else
			{
				ant = pos.get_posx();
				pos.set_posx((pos.get_posx()-1));
				if(pos.get_posx()<0)
					pos.set_posx(lims-1);
			}
			map.refresh(pos.get_posy(), ant, pos.get_posy(), pos.get_posx(), this);
		}
	}
	
	public void movesomehow()
	{
		
	}
	
	public void run()
	{
		while(true)
		{
			try 
			{
				sleep(retardo);
			} 
			catch (InterruptedException e) 
			{
				e.printStackTrace();
			}
			move();
		}
	}

}

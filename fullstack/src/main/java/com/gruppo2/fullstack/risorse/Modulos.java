package com.gruppo2.fullstack.risorse;

import java.util.ArrayList;
import java.util.List;

import com.gruppo2.fullstack.model.Modulo;

public class Modulos {

		public Modulos() {
			// TODO Auto-generated constructor stub
		}
		
		private List<Modulo> modulolist = new ArrayList<>();
		
		public void addModulo(Modulo modulo){
			
			this.modulolist.add(modulo);
			
			
		}


		
		
		public void removeModulo(Modulo modulo){
			
			this.modulolist.remove(modulo);
			
			
		}


		public boolean contiene(Modulo modulo) {
			return modulolist.contains(modulo);
			
		}



		public List<Modulo> getModulolist() {
			return modulolist;
		}



		public void setModulolist(List<Modulo> modulolist) {
			this.modulolist = modulolist;
		}



		@Override
		public String toString() {
			return "Modulos [modulolist=" + modulolist + "]";
		}
		
		
		
		
	}
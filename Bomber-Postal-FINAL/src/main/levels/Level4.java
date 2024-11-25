package main.levels;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.imageio.ImageIO;

import main.entities.Item;
import main.entities.Power_Up;
import main.entities.characters.Innocent;
import main.entities.characters.Military;
import main.entities.characters.Player;
import main.entities.characters.Police;
import main.entities.environment.Structure;
import main.entities.projectiles.Bomb;
import main.ui.Window;
import main.utils.FloatingText;

public class Level4 {

	
	// ----------------------- Declaracion de variables
	
	private int invalido = 0, setRemainingProgressObjects = 0, advanceFrame = 0, createObjects = 0;
    long lastPressedTime = 0;
    boolean canPress = true;
    private Image 
    
    	tronco,
    
    	
    	
    	N4F1BORDE, N4F1ESQ_IZQ_INF, N4F1ESQ_IZQ_SUP, N4F1ESQ_DER_INF, N4F1ESQ_DER_SUP, N4F1PA, N4F1SUELO, LOCKER, N4F1PA1, 
    	N4F1BORDE_ARRIBA, N4F1BORDE_ABAJO, N4F1BORDE_IZQ, N4F1BORDE_DER, 
    	
    	N4F2BORDE, N4F2ESQ_IZQ_INF, N4F2ESQ_IZQ_SUP, N4F2ESQ_DER_INF, N4F2ESQ_DER_SUP, N4F2PA, N4F2SUELO, N4F2BORDE_ARRIBA,
    	N4F2BORDE_ABAJO, N4F2BORDE_IZQ, N4F2BORDE_DER, carne,
    	
    	N4F3BORDE, N4F3ESQ_IZQ_INF, N4F3ESQ_IZQ_SUP, N4F3ESQ_DER_INF, N4F3ESQ_DER_SUP, N4F3PA, N4F3SUELO, N4F3BORDE_ARRIBA,
    	N4F3BORDE_ABAJO, N4F3BORDE_IZQ, N4F3BORDE_DER, cinta
    	
    
    	;
	// ----------------------- Fin de Declaracion de variables
	
	// Constructor
	public Level4() {
        try {
	      	  
              tronco = ImageIO.read(new File("assets/images/SpritesBomberPostal/Mapa/Dia3/1_Bosque/Tronco.png"));
              
              
        	
              
              N4F1BORDE_ARRIBA = ImageIO.read(new File("assets/images/SpritesBomberPostal/Mapa/Dia4/1_Comisaria/BordeComisaria.png"));
              N4F1BORDE_ABAJO = ImageIO.read(new File("assets/images/SpritesBomberPostal/Mapa/Dia4/1_Comisaria/BordeComisariaInferior.png"));
              N4F1BORDE_IZQ = ImageIO.read(new File("assets/images/SpritesBomberPostal/Mapa/Dia4/1_Comisaria/BordeComisariaIzquierda.png"));
              N4F1BORDE_DER = ImageIO.read(new File("assets/images/SpritesBomberPostal/Mapa/Dia4/1_Comisaria/BordeComisariaDerecha.png"));
              N4F1ESQ_IZQ_INF = ImageIO.read(new File("assets/images/SpritesBomberPostal/Mapa/Dia4/1_Comisaria/EsquinaComisariaInferiorIzquierda.png"));
              N4F1ESQ_IZQ_SUP = ImageIO.read(new File("assets/images/SpritesBomberPostal/Mapa/Dia4/1_Comisaria/EsquinaComisariaSuperiorIzquierda.png"));
              N4F1ESQ_DER_INF = ImageIO.read(new File("assets/images/SpritesBomberPostal/Mapa/Dia4/1_Comisaria/EsquinaComisariaInferiorDerecha.png"));
              N4F1ESQ_DER_SUP = ImageIO.read(new File("assets/images/SpritesBomberPostal/Mapa/Dia4/1_Comisaria/EsquinaComisariaSuperiorDerecha.png"));
              N4F1PA = ImageIO.read(new File("assets/images/SpritesBomberPostal/Mapa/Dia4/1_Comisaria/ParedComisaria.png"));
              N4F1PA1 = ImageIO.read(new File("assets/images/SpritesBomberPostal/Mapa/Dia4/1_Comisaria/ParedCelda.png"));
              N4F1SUELO = ImageIO.read(new File("assets/images/SpritesBomberPostal/Mapa/Dia4/1_Comisaria/PisoComisaria.png"));
              LOCKER = ImageIO.read(new File("assets/images/SpritesBomberPostal/Mapa/Dia4/1_Comisaria/Locker.png"));
              
              
               N4F2ESQ_IZQ_INF = ImageIO.read(new File("assets/images/SpritesBomberPostal/Mapa/Dia4/2_Carniceria/BordeCarniceriaEsquinaInferiorIzquierda.png"));
               N4F2ESQ_IZQ_SUP = ImageIO.read(new File("assets/images/SpritesBomberPostal/Mapa/Dia4/2_Carniceria/BordeCarniceriaEsquinaSuperiorIzquierda.png"));
               N4F2ESQ_DER_INF = ImageIO.read(new File("assets/images/SpritesBomberPostal/Mapa/Dia4/2_Carniceria/BordeCarniceriaEsquinaInferiorDerecha.png"));
               N4F2ESQ_DER_SUP = ImageIO.read(new File("assets/images/SpritesBomberPostal/Mapa/Dia4/2_Carniceria/BordeCarniceriaEsquinaSuperiorDerecha.png"));
               N4F2PA = ImageIO.read(new File("assets/images/SpritesBomberPostal/Mapa/Dia4/2_Carniceria/ParedCarniceria.png"));
               N4F2SUELO = ImageIO.read(new File("assets/images/SpritesBomberPostal/Mapa/Dia4/2_Carniceria/PisoCarniceria.png"));
               N4F2BORDE_ARRIBA = ImageIO.read(new File("assets/images/SpritesBomberPostal/Mapa/Dia4/2_Carniceria/BordeCarniceria.png"));
               N4F2BORDE_ABAJO = ImageIO.read(new File("assets/images/SpritesBomberPostal/Mapa/Dia4/2_Carniceria/BordeCarniceriaInferior.png"));
               N4F2BORDE_IZQ = ImageIO.read(new File("assets/images/SpritesBomberPostal/Mapa/Dia4/2_Carniceria/BordeCarniceriaIzquierda.png"));
               N4F2BORDE_DER = ImageIO.read(new File("assets/images/SpritesBomberPostal/Mapa/Dia4/2_Carniceria/BordeCarniceriaDerecha.png"));
              carne = ImageIO.read(new File("assets/images/SpritesBomberPostal/Mapa/Dia4/2_Carniceria/Carne.png"));
              
              N4F3ESQ_IZQ_INF = ImageIO.read(new File("assets/images/SpritesBomberPostal/Mapa/Dia4/3_Fabrica/FabricaEsquinaInferiorIzquierda.png"));
              N4F3ESQ_IZQ_SUP = ImageIO.read(new File("assets/images/SpritesBomberPostal/Mapa/Dia4/3_Fabrica/FabricaEsquinaSuperiorIzquierda.png"));
              N4F3ESQ_DER_INF = ImageIO.read(new File("assets/images/SpritesBomberPostal/Mapa/Dia4/3_Fabrica/FabricaEsquinaInferiorDerecha.png"));
              N4F3ESQ_DER_SUP = ImageIO.read(new File("assets/images/SpritesBomberPostal/Mapa/Dia4/3_Fabrica/FabricaEsquinaSuperiorDerecha.png"));
              N4F3PA = ImageIO.read(new File("assets/images/SpritesBomberPostal/Mapa/Dia4/3_Fabrica/ParedFabrica.png"));
              N4F3SUELO = ImageIO.read(new File("assets/images/SpritesBomberPostal/Mapa/Dia4/3_Fabrica/PisoFabrica.png"));
              N4F3BORDE_ARRIBA = ImageIO.read(new File("assets/images/SpritesBomberPostal/Mapa/Dia4/3_Fabrica/BordeFabricaSuperior.png"));
          	N4F3BORDE_ABAJO = ImageIO.read(new File("assets/images/SpritesBomberPostal/Mapa/Dia4/3_Fabrica/BordeFabricaInferior.png"));
          	N4F3BORDE_IZQ = ImageIO.read(new File("assets/images/SpritesBomberPostal/Mapa/Dia4/3_Fabrica/BordeFabricaIzquierda.png"));
          	N4F3BORDE_DER = ImageIO.read(new File("assets/images/SpritesBomberPostal/Mapa/Dia4/3_Fabrica/BordeFabricaDerecha.png"));
              cinta = ImageIO.read(new File("assets/images/SpritesBomberPostal/Mapa/Dia4/3_Fabrica/CintaTranportadora.png"));
              
              
              
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public void update(List<Structure> structures, int bloqueTam, int offsetX, int offsetY, boolean iPressed, Player player, List<Power_Up> powerups, List<Innocent> inocentes, List<Police> policias, List<Military> militares, List<Item> items, List<FloatingText> floatingText, List<Bomb> bombs) {			
		if(invalido != 1) {
	    	 //FRAME 1
			
				structures.add(new Structure(-10000, -10000 , bloqueTam, bloqueTam, tronco,0,powerups));
		    	 structures.add(new Structure(-10000, -10000 , bloqueTam, bloqueTam, tronco,0,powerups));
		    	 structures.add(new Structure(-10000, -10000 , bloqueTam, bloqueTam, tronco,0,powerups));
		    	 structures.add(new Structure(-10000, -10000 , bloqueTam, bloqueTam, tronco,0,powerups));
		    	 structures.add(new Structure(-10000, -10000 , bloqueTam, bloqueTam, tronco,0,powerups));
		    	 structures.add(new Structure(-10000, -10000 , bloqueTam, bloqueTam, tronco,0,powerups));
		    	 structures.add(new Structure(-10000, -10000 , bloqueTam, bloqueTam, tronco,0,powerups));
		    	 structures.add(new Structure(-10000, -10000 , bloqueTam, bloqueTam, tronco,0,powerups));
		    	 structures.add(new Structure(-10000, -10000 , bloqueTam, bloqueTam, tronco,0,powerups));
		    	 structures.add(new Structure(-10000, -10000 , bloqueTam, bloqueTam, tronco,0,powerups));
		    	 structures.add(new Structure(-10000, -10000 , bloqueTam, bloqueTam, tronco,0,powerups));
		    	 structures.add(new Structure(-10000, -10000 , bloqueTam, bloqueTam, tronco,0,powerups));
		    	 structures.add(new Structure(-10000, -10000 , bloqueTam, bloqueTam, tronco,0,powerups));
		    	 structures.add(new Structure(-10000, -10000 , bloqueTam, bloqueTam, tronco,0,powerups));
		    	 structures.add(new Structure(-10000, -10000 , bloqueTam, bloqueTam, tronco,0,powerups));
		    	 
		    	 
		    	 // FRAME 2
		    	 structures.add(new Structure(-10000, -10000 , bloqueTam, bloqueTam, tronco,0,powerups));
		    	 structures.add(new Structure(-10000, -10000 , bloqueTam, bloqueTam, tronco,0,powerups));
		    	 structures.add(new Structure(-10000, -10000 , bloqueTam, bloqueTam, tronco,0,powerups));
		    	 structures.add(new Structure(-10000, -10000 , bloqueTam, bloqueTam, tronco,0,powerups));
		    	 structures.add(new Structure(-10000, -10000 , bloqueTam, bloqueTam, tronco,0,powerups));
		    	 structures.add(new Structure(-10000, -10000 , bloqueTam, bloqueTam, tronco,0,powerups));
		    	 structures.add(new Structure(-10000, -10000 , bloqueTam, bloqueTam, tronco,0,powerups));
		    	 structures.add(new Structure(-10000, -10000 , bloqueTam, bloqueTam, tronco,0,powerups));
		    	 structures.add(new Structure(-10000, -10000 , bloqueTam, bloqueTam, tronco,0,powerups));
		    	 structures.add(new Structure(-10000, -10000 , bloqueTam, bloqueTam, tronco,0,powerups));
		    	 structures.add(new Structure(-10000, -10000 , bloqueTam, bloqueTam, tronco,0,powerups));
		    	 structures.add(new Structure(-10000, -10000 , bloqueTam, bloqueTam, tronco,0,powerups));
		    	 structures.add(new Structure(-10000, -10000 , bloqueTam, bloqueTam, tronco,0,powerups));
		    	 structures.add(new Structure(-10000, -10000 , bloqueTam, bloqueTam, tronco,0,powerups));
		    	 structures.add(new Structure(-10000, -10000 , bloqueTam, bloqueTam, tronco,0,powerups));


		    	 
		    	 // FRAME 3
		    	 
		    	 structures.add(new Structure(-10000, -10000 , bloqueTam, bloqueTam, tronco,0,powerups));
		    	 structures.add(new Structure(-10000, -10000 , bloqueTam, bloqueTam, tronco,0,powerups));
		    	 structures.add(new Structure(-10000, -10000 , bloqueTam, bloqueTam, tronco,0,powerups));
		    	 structures.add(new Structure(-10000, -10000 , bloqueTam, bloqueTam, tronco,0,powerups));
		    	 structures.add(new Structure(-10000, -10000 , bloqueTam, bloqueTam, tronco,0,powerups));
		    	 structures.add(new Structure(-10000, -10000 , bloqueTam, bloqueTam, tronco,0,powerups));
		    	 structures.add(new Structure(-10000, -10000 , bloqueTam, bloqueTam, tronco,0,powerups));
		    	 structures.add(new Structure(-10000, -10000 , bloqueTam, bloqueTam, tronco,0,powerups));
		    	 structures.add(new Structure(-10000, -10000 , bloqueTam, bloqueTam, tronco,0,powerups));
		    	 structures.add(new Structure(-10000, -10000 , bloqueTam, bloqueTam, tronco,0,powerups));
		    	 structures.add(new Structure(-10000, -10000 , bloqueTam, bloqueTam, tronco,0,powerups));
		    	 structures.add(new Structure(-10000, -10000 , bloqueTam, bloqueTam, tronco,0,powerups));
		    	 structures.add(new Structure(-10000, -10000 , bloqueTam, bloqueTam, tronco,0,powerups));
		    	 structures.add(new Structure(-10000, -10000 , bloqueTam, bloqueTam, tronco,0,powerups));
		    	 structures.add(new Structure(-10000, -10000 , bloqueTam, bloqueTam, tronco,0,powerups));
		    	 
		    	 structures.add(new Structure(-10000, -10000 , bloqueTam, bloqueTam, tronco,0,powerups));
		    	 structures.add(new Structure(-10000, -10000 , bloqueTam, bloqueTam, tronco,0,powerups));
		    	 structures.add(new Structure(-10000, -10000 , bloqueTam, bloqueTam, tronco,0,powerups));
		    	 structures.add(new Structure(-10000, -10000 , bloqueTam, bloqueTam, tronco,0,powerups));
		    	 structures.add(new Structure(-10000, -10000 , bloqueTam, bloqueTam, tronco,0,powerups));
		    	 structures.add(new Structure(-10000, -10000 , bloqueTam, bloqueTam, tronco,0,powerups));
		    	 structures.add(new Structure(-10000, -10000 , bloqueTam, bloqueTam, tronco,0,powerups));
		    	 structures.add(new Structure(-10000, -10000 , bloqueTam, bloqueTam, tronco,0,powerups));
		    	 structures.add(new Structure(-10000, -10000 , bloqueTam, bloqueTam, tronco,0,powerups));
		    	 structures.add(new Structure(-10000, -10000 , bloqueTam, bloqueTam, tronco,0,powerups));
		    	 structures.add(new Structure(-10000, -10000 , bloqueTam, bloqueTam, tronco,0,powerups));
		    	 structures.add(new Structure(-10000, -10000 , bloqueTam, bloqueTam, tronco,0,powerups));
		    	 structures.add(new Structure(-10000, -10000 , bloqueTam, bloqueTam, tronco,0,powerups));
		    	 structures.add(new Structure(-10000, -10000 , bloqueTam, bloqueTam, tronco,0,powerups));
		    	 structures.add(new Structure(-10000, -10000 , bloqueTam, bloqueTam, tronco,0,powerups));
		    	 

		    	 structures.add(new Structure(-10000, -10000 , bloqueTam, bloqueTam, tronco,0,powerups));
		    	 structures.add(new Structure(-10000, -10000 , bloqueTam, bloqueTam, tronco,0,powerups));
		    	 structures.add(new Structure(-10000, -10000 , bloqueTam, bloqueTam, tronco,0,powerups));
		    	 structures.add(new Structure(-10000, -10000 , bloqueTam, bloqueTam, tronco,0,powerups));
		    	 structures.add(new Structure(-10000, -10000 , bloqueTam, bloqueTam, tronco,0,powerups));
		    	 structures.add(new Structure(-10000, -10000 , bloqueTam, bloqueTam, tronco,0,powerups));
		    	 structures.add(new Structure(-10000, -10000 , bloqueTam, bloqueTam, tronco,0,powerups));
		    	 structures.add(new Structure(-10000, -10000 , bloqueTam, bloqueTam, tronco,0,powerups));
		    	 structures.add(new Structure(-10000, -10000 , bloqueTam, bloqueTam, tronco,0,powerups));
		    	 structures.add(new Structure(-10000, -10000 , bloqueTam, bloqueTam, tronco,0,powerups));
		    	 structures.add(new Structure(-10000, -10000 , bloqueTam, bloqueTam, tronco,0,powerups));
		    	 structures.add(new Structure(-10000, -10000 , bloqueTam, bloqueTam, tronco,0,powerups));
		    	 structures.add(new Structure(-10000, -10000 , bloqueTam, bloqueTam, tronco,0,powerups));
		    	 structures.add(new Structure(-10000, -10000 , bloqueTam, bloqueTam, tronco,0,powerups));
		    	 structures.add(new Structure(-10000, -10000 , bloqueTam, bloqueTam, tronco,0,powerups));
		    	 
	    	 invalido = 1;
		}
		
		
		
		// Actualizacion de entidades ------------------------------------------------------------------------------------
		
		for(Innocent inocentes1 : inocentes) {
         	inocentes1.update(structures, bloqueTam, player, player.bullets);
     	}
		
     	for(Police policias1: policias) {
     		policias1.update(structures, bloqueTam, player, player.bullets);
     	}
     	
     	for(Military militares1: militares) {
     		militares1.update(structures, bloqueTam, player, player.bullets);
     	}
     	
     	for (int x = 0; x < policias.size(); x++) {
     	    if (!policias.get(x).isAlive()) {
                floatingText.add(new FloatingText(policias.get(x).getX(), policias.get(x).getY(), "+50", Color.RED));
                Window.puntuacion+=50;
     	        policias.remove(x);
     	        x--; 

     	    }
     	}
     	
     	for (int x = 0; x < inocentes.size(); x++) {
     	    if (!inocentes.get(x).isAlive()) {
                floatingText.add(new FloatingText(inocentes.get(x).getX(), inocentes.get(x).getY(), "+25", Color.RED));
                Window.puntuacion+=25;
                inocentes.remove(x);
     	        x--;
     	    }
     	}
     	
     	for (int x = 0; x < militares.size(); x++) {
     	    if (!militares.get(x).isAlive()) {
                floatingText.add(new FloatingText(militares.get(x).getX(), militares.get(x).getY(), "+100", Color.RED));
                Window.puntuacion+=50;
     	        militares.remove(x);
     	        x--; 

     	    }
     	}
     	
     	// Fin de Actualizacion de entidades -----------------------------------------------------------------------------------
     	

     	// Reiteracion de codigo ----------------------------------
     	
     	for(Structure structures1 : structures) {
			structures1.width = bloqueTam;
			structures1.height = bloqueTam;
     	}
     	
     	// Fin de reiteracion de codigo ---------------------------

     	
     	// En el caso de que se recolecte algun objeto de tipo "progreso" la variable que controla los objetos se reduce en 1
     	for (Iterator<Item> iterator = items.iterator(); iterator.hasNext();) {
     	    Item item = iterator.next();
     	    item.update();
     	    if (item.getCollected() && item.getTipo() == 1) {
     	        iterator.remove(); // Remover el item de la lista
     	        Window.objetosRestantes--; // Disminuir objetos restantes
     	    }
     	}

	    Window.enemigosRestantes = inocentes.size() + policias.size() + militares.size();
    	
		if(Window.frameSeleccionado == 1) {
			
			
            
			
			if(createObjects == 0) {
				
		    	inocentes.add(new Innocent(bloqueTam * 7 + offsetX, bloqueTam * 5+ offsetY , bloqueTam, bloqueTam));
		    	
		    	  
				policias.add(new Police(bloqueTam * 1 + offsetX, bloqueTam * 3 + offsetY , bloqueTam, bloqueTam));
				policias.add(new Police(bloqueTam * 3 + offsetX, bloqueTam * 3 + offsetY , bloqueTam, bloqueTam));
				policias.add(new Police(bloqueTam * 6 + offsetX, bloqueTam * 2 + offsetY , bloqueTam, bloqueTam));
				policias.add(new Police(bloqueTam * 8 + offsetX, bloqueTam * 2 + offsetY , bloqueTam, bloqueTam));
				policias.add(new Police(bloqueTam * 8 + offsetX, bloqueTam * 4 + offsetY , bloqueTam, bloqueTam));
				policias.add(new Police(bloqueTam * 6 + offsetX, bloqueTam * 4 + offsetY , bloqueTam, bloqueTam));
				policias.add(new Police(bloqueTam * 13 + offsetX, bloqueTam * 1 + offsetY , bloqueTam, bloqueTam));
				policias.add(new Police(bloqueTam * 13 + offsetX, bloqueTam * 3 + offsetY , bloqueTam, bloqueTam));
				policias.add(new Police(bloqueTam * 1 + offsetX, bloqueTam * 7 + offsetY , bloqueTam, bloqueTam));
				policias.add(new Police(bloqueTam * 3 + offsetX, bloqueTam * 7 + offsetY , bloqueTam, bloqueTam));
				policias.add(new Police(bloqueTam * 13 + offsetX, bloqueTam * 7 + offsetY , bloqueTam, bloqueTam));
				policias.add(new Police(bloqueTam * 11 + offsetX, bloqueTam * 7 + offsetY , bloqueTam, bloqueTam));



				
				
		    	 items.add(new Item(bloqueTam * 2 + offsetX, bloqueTam * 7+ offsetY , bloqueTam, bloqueTam, player,1));
                 items.add(new Item(bloqueTam * 12 + offsetX, bloqueTam * 7+ offsetY , bloqueTam, bloqueTam, player,1));
                 
		    	
		    	 
		    	items.add(new Item(bloqueTam * 4 + offsetX, bloqueTam * 1+ offsetY , bloqueTam, bloqueTam, player,2));
		    	items.add(new Item(bloqueTam * 11 + offsetX, bloqueTam * 4+ offsetY , bloqueTam, bloqueTam, player,2));
		    	items.add(new Item(bloqueTam * 6 + offsetX, bloqueTam * 8+ offsetY , bloqueTam, bloqueTam, player,2));
		    	items.add(new Item(bloqueTam * 7 + offsetX, bloqueTam * 8+ offsetY , bloqueTam, bloqueTam, player,2));
		    	items.add(new Item(bloqueTam * 8 + offsetX, bloqueTam * 8+ offsetY , bloqueTam, bloqueTam, player,2));
		    	items.add(new Item(bloqueTam * 8 + offsetX, bloqueTam * 6+ offsetY , bloqueTam, bloqueTam, player,2));
		    	items.add(new Item(bloqueTam * 6 + offsetX, bloqueTam * 6+ offsetY , bloqueTam, bloqueTam, player,2));

		    	 	
		    	 
				
				
				
				
				
		    	 createObjects = 1;
			}

			if(setRemainingProgressObjects == 0) {
			    for(Item items1: items ) {
			    	if(items1.getTipo() == 1) {
			    		Window.objetosRestantes++;
			    	}
			    }
			    setRemainingProgressObjects = 1;
			}

	    	
			structures.get(0).x = bloqueTam * 1+ offsetX;
		    structures.get(0).y = bloqueTam * 2+ offsetY;
		    structures.get(0).image = N4F1PA;
		   
		    
		    structures.get(1).x = bloqueTam * 3+ offsetX;
		    structures.get(1).y = bloqueTam * 1+ offsetY;
		    structures.get(1).image = N4F1PA;
		    
		    structures.get(2).x = bloqueTam * 4+ offsetX;
		    structures.get(2).y = bloqueTam * 2+ offsetY;
		    structures.get(2).image = N4F1PA;
		    
		    structures.get(3).x = bloqueTam * 2+ offsetX;
		    structures.get(3).y = bloqueTam * 3+ offsetY;
		    structures.get(3).image = N4F1PA;
		    
		    structures.get(4).x = bloqueTam * 4+ offsetX;
		    structures.get(4).y = bloqueTam * 4+ offsetY;
		    structures.get(4).image = N4F1PA;
		    
		    structures.get(5).x = bloqueTam * 7+ offsetX;
		    structures.get(5).y = bloqueTam * 2+ offsetY;
		    structures.get(5).image = N4F1PA;
		    
		    structures.get(6).x = bloqueTam * 7+ offsetX;
		    structures.get(6).y = bloqueTam * 3+ offsetY;
		    structures.get(6).image = N4F1PA;
		    
		    structures.get(7).x = bloqueTam * 7+ offsetX;
		    structures.get(7).y = bloqueTam * 4+ offsetY;
		    structures.get(7).image = N4F1PA;
		    
		    structures.get(8).x = bloqueTam * 6+ offsetX;
		    structures.get(8).y = bloqueTam * 3+ offsetY;
		    structures.get(8).image = N4F1PA;
		    
		    structures.get(9).x = bloqueTam * 8+ offsetX;
		    structures.get(9).y = bloqueTam * 3+ offsetY;
		    structures.get(9).image = N4F1PA;
		    
		    structures.get(10).x = bloqueTam * 1+ offsetX;
		    structures.get(10).y = bloqueTam * 5+ offsetY;
		    structures.get(10).image = N4F1PA;
		    
		    structures.get(11).x = bloqueTam * 2+ offsetX;
		    structures.get(11).y = bloqueTam * 5+ offsetY;
		    structures.get(11).image = N4F1PA;
		    
		    structures.get(12).x = bloqueTam * 3+ offsetX;
		    structures.get(12).y = bloqueTam * 5+ offsetY;
		    structures.get(12).image = N4F1PA;
		    
		    structures.get(13).x = bloqueTam * 4+ offsetX;
		    structures.get(13).y = bloqueTam * 5+ offsetY;
		    structures.get(13).image = N4F1PA;
		    
		    structures.get(14).x = bloqueTam * 5+ offsetX;
		    structures.get(14).y = bloqueTam * 5+ offsetY;
		    structures.get(14).image = N4F1PA;
		    
		    structures.get(15).x = bloqueTam * 10+ offsetX;
		    structures.get(15).y = bloqueTam * 1+ offsetY;
		    structures.get(15).image = N4F1PA;
		    
		    structures.get(16).x = bloqueTam * 10+ offsetX;
		    structures.get(16).y = bloqueTam * 2+ offsetY;
		    structures.get(16).image = N4F1PA;
		    
		    structures.get(17).x = bloqueTam * 10+ offsetX;
		    structures.get(17).y = bloqueTam * 3+ offsetY;
		    structures.get(17).image = N4F1PA;
		    
		    structures.get(18).x = bloqueTam * 13+ offsetX;
		    structures.get(18).y = bloqueTam * 2+ offsetY;
		    structures.get(18).image = N4F1PA;
		    
		    structures.get(19).x = bloqueTam * 13+ offsetX;
		    structures.get(19).y = bloqueTam * 4+ offsetY;
		    structures.get(19).image = N4F1PA;
		    
		    structures.get(20).x = bloqueTam * 13+ offsetX;
		    structures.get(20).y = bloqueTam * 5+ offsetY;
		    structures.get(20).image = N4F1PA;
		    
		    structures.get(21).x = bloqueTam * 12+ offsetX;
		    structures.get(21).y = bloqueTam * 5+ offsetY;
		    structures.get(21).image = N4F1PA;
		    
		    structures.get(22).x = bloqueTam * 11+ offsetX;
		    structures.get(22).y = bloqueTam * 5+ offsetY;
		    structures.get(22).image = N4F1PA;
		    
		    structures.get(23).x = bloqueTam * 10+ offsetX;
		    structures.get(23).y = bloqueTam * 5+ offsetY;
		    structures.get(23).image = N4F1PA;
		    
		    structures.get(24).x = bloqueTam * 9+ offsetX;
		    structures.get(24).y = bloqueTam * 5+ offsetY;
		    structures.get(24).image = N4F1PA;
		    
		    structures.get(25).x = bloqueTam * 10+ offsetX;
		    structures.get(25).y = bloqueTam * 6+ offsetY;
		    structures.get(25).image = N4F1PA;
		    
		    structures.get(26).x = bloqueTam * 10+ offsetX;
		    structures.get(26).y = bloqueTam * 8+ offsetY;
		    structures.get(26).image = N4F1PA;
		    
		    structures.get(27).x = bloqueTam * 8+ offsetX;
		    structures.get(27).y = bloqueTam * 7+ offsetY;
		    structures.get(27).image = N4F1PA;
		    
		    structures.get(28).x = bloqueTam * 8+ offsetX;
		    structures.get(28).y = bloqueTam * 7+ offsetY;
		    structures.get(28).image = N4F1PA;
		    
		    structures.get(29).x = bloqueTam * 7+ offsetX;
		    structures.get(29).y = bloqueTam * 7+ offsetY;
		    structures.get(29).image = N4F1PA;
		    
		    structures.get(30).x = bloqueTam * 6+ offsetX;
		    structures.get(30).y = bloqueTam * 7+ offsetY;
		    structures.get(30).image = N4F1PA;
		    
		    structures.get(31).x = bloqueTam * 7+ offsetX;
		    structures.get(31).y = bloqueTam * 6+ offsetY;
		    structures.get(31).image = N4F1PA;
		    
		    structures.get(32).x = bloqueTam * 4+ offsetX;
		    structures.get(32).y = bloqueTam * 6+ offsetY;
		    structures.get(32).image = N4F1PA;
		    
		    structures.get(33).x = bloqueTam * 4+ offsetX;
		    structures.get(33).y = bloqueTam * 8+ offsetY;
		    structures.get(33).image = N4F1PA;
		    
		    
		    
		    structures.get(34).x = bloqueTam * 3+ offsetX;
		    structures.get(34).y = bloqueTam * 2+ offsetY;
		    structures.get(34).image = LOCKER;
		    structures.get(34).tipo = 1;
		    
		    structures.get(35).x = bloqueTam * 2+ offsetX;
		    structures.get(35).y = bloqueTam * 4+ offsetY;
		    structures.get(35).image = LOCKER;
		    structures.get(35).tipo = 1;
		    
		    structures.get(36).x = -10000;
		    structures.get(36).y = -10000;
		    structures.get(36).image = LOCKER;
		    structures.get(36).tipo = 1;
		    
		    structures.get(37).x = -10000;
		    structures.get(37).y = -10000;
		    structures.get(37).image = LOCKER;
		    structures.get(37).tipo = 1;
		    
		    structures.get(38).x = bloqueTam * 5+ offsetX;
		    structures.get(38).y = bloqueTam * 3+ offsetY;
		    structures.get(38).image = LOCKER;
		    structures.get(38).tipo = 1;
		    
		    structures.get(39).x = bloqueTam * 7+ offsetX;
		    structures.get(39).y = bloqueTam * 1+ offsetY;
		    structures.get(39).image = N4F1PA;
		    
		    
		    structures.get(40).x = -10000;
		    structures.get(40).y = -10000;
		    structures.get(40).image = LOCKER;
		    structures.get(40).tipo = 1;
		    
		    structures.get(41).x = bloqueTam * 9+ offsetX;
		    structures.get(41).y = bloqueTam * 3+ offsetY;
		    structures.get(41).image = LOCKER;
		    structures.get(41).tipo = 1;
		    
		    structures.get(42).x = bloqueTam * 10+ offsetX;
		    structures.get(42).y = bloqueTam * 4+ offsetY;
		    structures.get(42).image = LOCKER;
		    structures.get(42).tipo = 1;
		    
		    structures.get(43).x = bloqueTam * 12+ offsetX;
		    structures.get(43).y = bloqueTam * 4+ offsetY;
		    structures.get(43).image = LOCKER;
		    structures.get(43).tipo = 1;
		    
		    structures.get(44).x = bloqueTam * 9+ offsetX;
		    structures.get(44).y = bloqueTam * 6+ offsetY;
		    structures.get(44).image = LOCKER;
		    structures.get(44).tipo = 1;
		    
		    structures.get(45).x = bloqueTam * 9+ offsetX;
		    structures.get(45).y = bloqueTam * 7+ offsetY;
		    structures.get(45).image = LOCKER;
		    structures.get(45).tipo = 1;
		    
		    structures.get(46).x = bloqueTam * 9+ offsetX;
		    structures.get(46).y = bloqueTam * 8+ offsetY;
		    structures.get(46).image = LOCKER;
		    structures.get(46).tipo = 1;
		    
		    structures.get(47).x = bloqueTam * 5+ offsetX;
		    structures.get(47).y = bloqueTam * 8+ offsetY;
		    structures.get(47).image = LOCKER;
		    structures.get(47).tipo = 1;
		    
		    structures.get(48).x = bloqueTam * 5+ offsetX;
		    structures.get(48).y = bloqueTam * 7+ offsetY;
		    structures.get(48).image = LOCKER;
		    structures.get(48).tipo = 1;
		   
		    structures.get(49).x = bloqueTam * 5+ offsetX;
		    structures.get(49).y = bloqueTam * 6+ offsetY;
		    structures.get(49).image = LOCKER;
		    structures.get(49).tipo = 1;
		    
		    structures.get(50).x = bloqueTam * 1+ offsetX;
		    structures.get(50).y = bloqueTam * 6+ offsetY;
		    structures.get(50).image = N4F1PA1;
		    structures.get(50).tipo = 0;
		    
		    structures.get(51).x = bloqueTam * 2+ offsetX;
		    structures.get(51).y = bloqueTam * 6+ offsetY;
		    structures.get(51).image = N4F1PA1;
		    structures.get(51).tipo = 0;
		    
		    structures.get(52).x = bloqueTam * 3+ offsetX;
		    structures.get(52).y = bloqueTam * 6+ offsetY;
		    structures.get(52).image = N4F1PA1;
		    structures.get(52).tipo = 0;
		    
		    structures.get(53).x = bloqueTam * 1+ offsetX;
		    structures.get(53).y = bloqueTam * 8+ offsetY;
		    structures.get(53).image = N4F1PA1;
		    structures.get(53).tipo = 0;
		   
		    structures.get(54).x = bloqueTam * 2+ offsetX;
		    structures.get(54).y = bloqueTam * 8+ offsetY;
		    structures.get(54).image = N4F1PA1;
		    structures.get(54).tipo = 0;
		    
		    structures.get(55).x = bloqueTam * 3+ offsetX;
		    structures.get(55).y = bloqueTam * 8+ offsetY;
		    structures.get(55).image = N4F1PA1;
		    structures.get(55).tipo = 0;
		    
		    structures.get(56).x = bloqueTam * 13+ offsetX;
		    structures.get(56).y = bloqueTam * 8+ offsetY;
		    structures.get(56).image = N4F1PA1;
		    structures.get(56).tipo = 0;
		    
		    structures.get(57).x = bloqueTam * 12+ offsetX;
		    structures.get(57).y = bloqueTam * 8+ offsetY;
		    structures.get(57).image = N4F1PA1;
		    structures.get(57).tipo = 0;
		    
		    structures.get(58).x = bloqueTam * 11+ offsetX;
		    structures.get(58).y = bloqueTam * 8+ offsetY;
		    structures.get(58).image = N4F1PA1;
		    structures.get(58).tipo = 0;
		    
		    structures.get(59).x = bloqueTam * 11+ offsetX;
		    structures.get(59).y = bloqueTam * 6+ offsetY;
		    structures.get(59).image = N4F1PA1;
		    structures.get(59).tipo = 0;
		    
		    structures.get(60).x = bloqueTam * 12+ offsetX;
		    structures.get(60).y = bloqueTam * 6+ offsetY;
		    structures.get(60).image = N4F1PA1;
		    structures.get(60).tipo = 0;
		    
		    structures.get(61).x = bloqueTam * 13+ offsetX;
		    structures.get(61).y = bloqueTam * 6+ offsetY;
		    structures.get(61).image = N4F1PA1;
		    structures.get(61).tipo = 0;
		    
		    structures.get(62).x = bloqueTam * 11+ offsetX;
		    structures.get(62).y = bloqueTam * 1+ offsetY;
		    structures.get(62).image = N4F1PA1;
		    structures.get(62).tipo = 0;
		    
		    structures.get(63).x = bloqueTam * 11+ offsetX;
		    structures.get(63).y = bloqueTam * 2+ offsetY;
		    structures.get(63).image = N4F1PA1;
		    structures.get(63).tipo = 0;
		    
		    structures.get(64).x = bloqueTam * 11+ offsetX;
		    structures.get(64).y = bloqueTam * 3+ offsetY;
		    structures.get(64).image = N4F1PA1;
		    structures.get(64).tipo = 0;
		    
		    structures.get(65).x = bloqueTam * 6+ offsetX;
		    structures.get(65).y = bloqueTam * 1+ offsetY;
		    structures.get(65).image = N4F1PA;
		    
		    structures.get(66).x = bloqueTam * 8+ offsetX;
		    structures.get(66).y = bloqueTam * 1+ offsetY;
		    structures.get(66).image = N4F1PA;
		    
		    structures.get(67).x = bloqueTam * 6+ offsetX;
		    structures.get(67).y = bloqueTam * 5+ offsetY;
		    structures.get(67).image = LOCKER;
		    structures.get(67).tipo = 1;
		    
		   
		    structures.get(68).x = bloqueTam * 8+ offsetX;
		    structures.get(68).y = bloqueTam * 5+ offsetY;
		    structures.get(68).image = LOCKER;
		    structures.get(68).tipo = 1;
		    
		    structures.get(69).x = bloqueTam * 5+ offsetX;
		    structures.get(69).y = bloqueTam * 1+ offsetY;
		    structures.get(69).image = LOCKER;
		    structures.get(69).tipo = 1;
		    
		    structures.get(70).x = bloqueTam * 9+ offsetX;
		    structures.get(70).y = bloqueTam * 1+ offsetY;
		    structures.get(70).image = LOCKER;
		    structures.get(70).tipo = 1;
		    
		    
		    
		    for (int i = 0; i < structures.size(); i++) {
                if (structures.get(i).image == N4F1PA) {
                    structures.get(i).tipo = 0;
                }    

		}   
		}
		    
		else if(Window.frameSeleccionado == 2) {

if(createObjects == 0) {
				
		    	
		    	
	inocentes.add(new Innocent(bloqueTam * 3 + offsetX, bloqueTam * 2+ offsetY , bloqueTam, bloqueTam));
	inocentes.add(new Innocent(bloqueTam * 4 + offsetX, bloqueTam * 7+ offsetY , bloqueTam, bloqueTam));
	inocentes.add(new Innocent(bloqueTam * 7 + offsetX, bloqueTam * 8+ offsetY , bloqueTam, bloqueTam));
	inocentes.add(new Innocent(bloqueTam * 6 + offsetX, bloqueTam * 4+ offsetY , bloqueTam, bloqueTam));

	
		    policias.add(new Police(bloqueTam * 9 + offsetX, bloqueTam * 2 + offsetY , bloqueTam, bloqueTam));
		    policias.add(new Police(bloqueTam * 9 + offsetX, bloqueTam * 4 + offsetY , bloqueTam, bloqueTam));
		    policias.add(new Police(bloqueTam * 9 + offsetX, bloqueTam * 6 + offsetY , bloqueTam, bloqueTam));
		    policias.add(new Police(bloqueTam * 9 + offsetX, bloqueTam * 8 + offsetY , bloqueTam, bloqueTam));
		    policias.add(new Police(bloqueTam * 13 + offsetX, bloqueTam * 8 + offsetY , bloqueTam, bloqueTam));
		    policias.add(new Police(bloqueTam * 13 + offsetX, bloqueTam * 4 + offsetY , bloqueTam, bloqueTam));
		    policias.add(new Police(bloqueTam * 13 + offsetX, bloqueTam * 2 + offsetY , bloqueTam, bloqueTam));

		   militares.add(new Military(bloqueTam * 7 + offsetX, bloqueTam * 2 + offsetY , bloqueTam, bloqueTam));
		   militares.add(new Military(bloqueTam * 12 + offsetX, bloqueTam * 1 + offsetY , bloqueTam, bloqueTam));
		   militares.add(new Military(bloqueTam * 8 + offsetX, bloqueTam * 7 + offsetY , bloqueTam, bloqueTam));

		   
		    	 items.add(new Item(bloqueTam * 11 + offsetX, bloqueTam * 1+ offsetY , bloqueTam, bloqueTam, player,1));


		    	 items.add(new Item(bloqueTam * 3 + offsetX, bloqueTam * 6+ offsetY , bloqueTam, bloqueTam, player,1));
		    	 
		    	 items.add(new Item(bloqueTam * 7 + offsetX, bloqueTam * 4+ offsetY , bloqueTam, bloqueTam, player,2));
		    	 items.add(new Item(bloqueTam * 10 + offsetX, bloqueTam * 2+ offsetY , bloqueTam, bloqueTam, player,2));
		    	 items.add(new Item(bloqueTam * 10 + offsetX, bloqueTam * 5+ offsetY , bloqueTam, bloqueTam, player,2));
		    	 items.add(new Item(bloqueTam * 10 + offsetX, bloqueTam * 7+ offsetY , bloqueTam, bloqueTam, player,2));




		    	 createObjects = 1;
			}
			
			if(setRemainingProgressObjects == 0) {
			    for(Item items1: items ) {
			    	if(items1.getTipo() == 1) {
			    		Window.objetosRestantes++;
			    	}
			    }
			    setRemainingProgressObjects = 1;
			}
						
			structures.get(0).x = bloqueTam * 2 + offsetX;
		    structures.get(0).y = bloqueTam * 1+ offsetY;
		    structures.get(0).image = N4F2PA;
		    
		    structures.get(1).x = bloqueTam * 2 + offsetX;
		    structures.get(1).y = bloqueTam * 2+ offsetY;
		    structures.get(1).image = N4F2PA;
		    
		    structures.get(2).x = bloqueTam * 2 + offsetX;
		    structures.get(2).y = bloqueTam * 3+ offsetY;
		    structures.get(2).image = N4F2PA;
		    
		    structures.get(3).x = bloqueTam * 3 + offsetX;
		    structures.get(3).y = bloqueTam * 3+ offsetY;
		    structures.get(3).image = N4F2PA;
		    
		    structures.get(4).x = bloqueTam * 4 + offsetX;
		    structures.get(4).y = bloqueTam * 2+ offsetY;
		    structures.get(4).image = N4F2PA;
		    
		    structures.get(5).x = bloqueTam * 6 + offsetX;
		    structures.get(5).y = bloqueTam * 1+ offsetY;
		    structures.get(5).image = N4F2PA;
		    
		    structures.get(6).x = bloqueTam * 7 + offsetX;
		    structures.get(6).y = bloqueTam * 1+ offsetY;
		    structures.get(6).image = N4F2PA;
		    
		    structures.get(7).x = bloqueTam * 8 + offsetX;
		    structures.get(7).y = bloqueTam * 1+ offsetY;
		    structures.get(7).image = N4F2PA;
		    
		    structures.get(8).x = bloqueTam * 9 + offsetX;
		    structures.get(8).y = bloqueTam * 1+ offsetY;
		    structures.get(8).image = N4F2PA;
		    
		    structures.get(9).x = bloqueTam * 10 + offsetX;
		    structures.get(9).y = bloqueTam * 1+ offsetY;
		    structures.get(9).image = N4F2PA;
		    
		    
		    structures.get(10).x = bloqueTam * 5 + offsetX;
		    structures.get(10).y = bloqueTam * 4+ offsetY;
		    structures.get(10).image = N4F2PA;
		    
		    structures.get(11).x = bloqueTam * 6 + offsetX;
		    structures.get(11).y = bloqueTam * 3+ offsetY;
		    structures.get(11).image = N4F2PA;
		    
		    structures.get(12).x = bloqueTam * 6 + offsetX;
		    structures.get(12).y = bloqueTam * 5+ offsetY;
		    structures.get(12).image = N4F2PA;
		    
		    structures.get(13).x = bloqueTam * 7 + offsetX;
		    structures.get(13).y = bloqueTam * 5+ offsetY;
		    structures.get(13).image = N4F2PA;
		    
		    structures.get(14).x = bloqueTam * 7 + offsetX;
		    structures.get(14).y = bloqueTam * 3+ offsetY;
		    structures.get(14).image = N4F2PA;
		    
		    structures.get(15).x = bloqueTam * 9 + offsetX;
		    structures.get(15).y = bloqueTam * 3+ offsetY;
		    structures.get(15).image = N4F2PA;
		    
		    structures.get(16).x = bloqueTam * 9 + offsetX;
		    structures.get(16).y = bloqueTam * 5+ offsetY;
		    structures.get(16).image = N4F2PA;

		    structures.get(17).x = bloqueTam * 9 + offsetX;
		    structures.get(17).y = bloqueTam * 7+ offsetY;
		    structures.get(17).image = N4F2PA;
		    
		    structures.get(18).x = bloqueTam * 7 + offsetX;
		    structures.get(18).y = bloqueTam * 7+ offsetY;
		    structures.get(18).image = N4F2PA;
		    
		    structures.get(19).x = bloqueTam * 5 + offsetX;
		    structures.get(19).y = bloqueTam * 7+ offsetY;
		    structures.get(19).image = N4F2PA;
		    
		    structures.get(20).x = bloqueTam * 5 + offsetX;
		    structures.get(20).y = bloqueTam * 7+ offsetY;
		    structures.get(20).image = N4F2PA;
		    
		    structures.get(21).x = bloqueTam * 4 + offsetX;
		    structures.get(21).y = bloqueTam * 6+ offsetY;
		    structures.get(21).image = N4F2PA;
		    
		    structures.get(22).x = bloqueTam * 3 + offsetX;
		    structures.get(22).y = bloqueTam * 5+ offsetY;
		    structures.get(22).image = N4F2PA;
			
		    structures.get(23).x = bloqueTam * 2 + offsetX;
		    structures.get(23).y = bloqueTam * 5+ offsetY;
		    structures.get(23).image = N4F2PA;
		    
		    structures.get(24).x = bloqueTam * 1 + offsetX;
		    structures.get(24).y = bloqueTam * 5+ offsetY;
		    structures.get(24).image = N4F2PA;
		    
		    structures.get(25).x = bloqueTam * 2 + offsetX;
		    structures.get(25).y = bloqueTam * 7+ offsetY;
		    structures.get(25).image = N4F2PA;

		    structures.get(26).x = bloqueTam * 3 + offsetX;
		    structures.get(26).y = bloqueTam * 7+ offsetY;
		    structures.get(26).image = N4F2PA;
		    
		    structures.get(27).x = bloqueTam * 12 + offsetX;
		    structures.get(27).y = bloqueTam * 7+ offsetY;
		    structures.get(27).image = N4F2PA;
		    
		    structures.get(28).x = bloqueTam * 11 + offsetX;
		    structures.get(28).y = bloqueTam * 7+ offsetY;
		    structures.get(28).image = N4F2PA;
		    
		    structures.get(29).x = bloqueTam * 12 + offsetX;
		    structures.get(29).y = bloqueTam * 6+ offsetY;
		    structures.get(29).image = N4F2PA;
		    
		    structures.get(30).x = bloqueTam * 11 + offsetX;
		    structures.get(30).y = bloqueTam * 6+ offsetY;
		    structures.get(30).image = N4F2PA;
		    
		    structures.get(31).x = bloqueTam * 12 + offsetX;
		    structures.get(31).y = bloqueTam * 5+ offsetY;
		    structures.get(31).image = N4F2PA;
		    
		    structures.get(32).x = bloqueTam * 12 + offsetX;
		    structures.get(32).y = bloqueTam * 3+ offsetY;
		    structures.get(32).image = N4F2PA;
		    
		    structures.get(33).x = bloqueTam * 12 + offsetX;
		    structures.get(33).y = bloqueTam * 2+ offsetY;
		    structures.get(33).image = N4F2PA;
		    
		    structures.get(34).x = bloqueTam * 11 + offsetX;
		    structures.get(34).y = bloqueTam * 2+ offsetY;
		    structures.get(34).image = N4F2PA;
		    
		    structures.get(35).x = bloqueTam * 11 + offsetX;
		    structures.get(35).y = bloqueTam * 3+ offsetY;
		    structures.get(35).image = N4F2PA;
		    
		    
		    
		    structures.get(36).x = bloqueTam * 1 + offsetX;
		    structures.get(36).y = bloqueTam * 6+ offsetY;
		    structures.get(36).image = carne;
		    structures.get(36).tipo = 1;
		    
		    structures.get(37).x = bloqueTam * 2 + offsetX;
		    structures.get(37).y = bloqueTam * 6+ offsetY;
		    structures.get(37).image = carne;
		    structures.get(37).tipo = 1;
		    
		    structures.get(38).x = bloqueTam * 1 + offsetX;
		    structures.get(38).y = bloqueTam * 7+ offsetY;
		    structures.get(38).image = carne;
		    structures.get(38).tipo = 1;
		    
		    structures.get(39).x = bloqueTam * 1 + offsetX;
		    structures.get(39).y = bloqueTam * 8+ offsetY;
		    structures.get(39).image = carne;
		    structures.get(39).tipo = 1;
		    
		    structures.get(40).x = bloqueTam * 5 + offsetX;
		    structures.get(40).y = bloqueTam * 8+ offsetY;
		    structures.get(40).image = carne;
		    structures.get(40).tipo = 1;
		    
		    structures.get(41).x = bloqueTam * 6 + offsetX;
		    structures.get(41).y = bloqueTam * 6+ offsetY;
		    structures.get(41).image = carne;
		    structures.get(41).tipo = 1;
		    
		    structures.get(42).x = bloqueTam * 8 + offsetX;
		    structures.get(42).y = bloqueTam * 6+ offsetY;
		    structures.get(42).image = carne;
		    structures.get(42).tipo = 1;
		    
		    structures.get(43).x = bloqueTam * 8 + offsetX;
		    structures.get(43).y = bloqueTam * 8+ offsetY;
		    structures.get(43).image = carne;
		    structures.get(43).tipo = 1;
		    
		    structures.get(44).x = bloqueTam * 5 + offsetX;
		    structures.get(44).y = bloqueTam * 2+ offsetY;
		    structures.get(44).image = carne;
		    structures.get(44).tipo = 1;
		    
		    structures.get(45).x = -10000;
		    structures.get(45).y = -10000;
		    structures.get(45).image = carne;
		    structures.get(45).tipo = 1;
		    
		    structures.get(46).x = bloqueTam * 8 + offsetX;
		    structures.get(46).y = bloqueTam * 2+ offsetY;
		    structures.get(46).image = carne;
		    structures.get(46).tipo = 1;
		    
		    structures.get(47).x = bloqueTam * 8 + offsetX;
		    structures.get(47).y = bloqueTam * 4 + offsetY;
		    structures.get(47).image = carne;
		    structures.get(47).tipo = 1;
		    
		    structures.get(48).x = bloqueTam * 11 + offsetX;
		    structures.get(48).y = bloqueTam * 4 + offsetY;
		    structures.get(48).image = carne;
		    structures.get(48).tipo = 1;
		    
		    structures.get(49).x = bloqueTam * 11 + offsetX;
		    structures.get(49).y = bloqueTam * 5 + offsetY;
		    structures.get(49).image = carne;
		    structures.get(49).tipo = 1;
		    
		    structures.get(50).x = bloqueTam * 13 + offsetX;
		    structures.get(50).y = bloqueTam * 1 + offsetY;
		    structures.get(50).image = carne;
		    structures.get(50).tipo = 1;
		    
		    structures.get(51).x = bloqueTam * 11 + offsetX;
		    structures.get(51).y = bloqueTam * 8 + offsetY;
		    structures.get(51).image = carne;
		    structures.get(51).tipo = 1;
		    
		    
		    
		    
		    for (int i = 0; i < structures.size(); i++) {
                if (structures.get(i).image == N4F2PA) {
                    structures.get(i).tipo = 0;
                }    

		    }   
		    
		    for (int i = 52; i < structures.size(); i++) {
		    	structures.get(i).x = -10000;
		    	structures.get(i).y = -10000;
		    }   
		    
		    
		}
		    else if(Window.frameSeleccionado == 3) {
		    	if(createObjects == 0) {
					
			    	// inocentes.add(new Innocent(bloqueTam * 5 + offsetX, bloqueTam * 2+ offsetY , bloqueTam, bloqueTam));
			    	 

			    	 
			    	
			    	// policias.add(new Police(bloqueTam * 2 + offsetX, bloqueTam * 4 + offsetY , bloqueTam, bloqueTam));
			    	 

			    	 
			   militares.add(new Military(bloqueTam * 7 + offsetX, bloqueTam * 1 + offsetY , bloqueTam, bloqueTam));
			   militares.add(new Military(bloqueTam * 9 + offsetX, bloqueTam * 2 + offsetY , bloqueTam, bloqueTam)); 		
			   militares.add(new Military(bloqueTam * 12 + offsetX, bloqueTam * 1 + offsetY , bloqueTam, bloqueTam)); 		
			   militares.add(new Military(bloqueTam * 2 + offsetX, bloqueTam * 5 + offsetY , bloqueTam, bloqueTam)); 		
			   militares.add(new Military(bloqueTam * 6 + offsetX, bloqueTam * 5 + offsetY , bloqueTam, bloqueTam)); 		 		
			   militares.add(new Military(bloqueTam * 6 + offsetX, bloqueTam * 8 + offsetY , bloqueTam, bloqueTam)); 		


				   
			   items.add(new Item(bloqueTam * 2 + offsetX, bloqueTam * 1+ offsetY , bloqueTam, bloqueTam, player,2));
			   items.add(new Item(bloqueTam * 1 + offsetX, bloqueTam * 2+ offsetY , bloqueTam, bloqueTam, player,2));
			   items.add(new Item(bloqueTam * 9 + offsetX, bloqueTam * 1+ offsetY , bloqueTam, bloqueTam, player,2));
			   items.add(new Item(bloqueTam * 5 + offsetX, bloqueTam * 4+ offsetY , bloqueTam, bloqueTam, player,2));
			   items.add(new Item(bloqueTam * 3 + offsetX, bloqueTam * 8+ offsetY , bloqueTam, bloqueTam, player,2));
			   items.add(new Item(bloqueTam * 4 + offsetX, bloqueTam * 7+ offsetY , bloqueTam, bloqueTam, player,2));
			   items.add(new Item(bloqueTam * 11 + offsetX, bloqueTam * 8+ offsetY , bloqueTam, bloqueTam, player,2));
			   items.add(new Item(bloqueTam * 12 + offsetX, bloqueTam * 8+ offsetY , bloqueTam, bloqueTam, player,2));
			   items.add(new Item(bloqueTam * 13 + offsetX, bloqueTam * 8+ offsetY , bloqueTam, bloqueTam, player,2));
			   items.add(new Item(bloqueTam * 12 + offsetX, bloqueTam * 7+ offsetY , bloqueTam, bloqueTam, player,2));

			    			
			    	 
			   	
			   	items.add(new Item(bloqueTam * 8 + offsetX, bloqueTam * 7+ offsetY , bloqueTam, bloqueTam, player,1));
			    items.add(new Item(bloqueTam * 9 + offsetX, bloqueTam * 8+ offsetY , bloqueTam, bloqueTam, player,1));


		
			    



			    	
			    	 
			    	// items.add(new Item(bloqueTam * 13 + offsetX, bloqueTam * 2+ offsetY , bloqueTam, bloqueTam, player,1));

			    	 
			    	 
					
					
					
					
					
			    	 createObjects = 1;
				}
		    	
		    	
				if(setRemainingProgressObjects == 0) {
				    for(Item items1: items ) {
				    	if(items1.getTipo() == 1) {
				    		Window.objetosRestantes++;
				    	}
				    }
				    setRemainingProgressObjects = 1;
				}
				
				
				structures.get(0).x = bloqueTam * 2 + offsetX;
			    structures.get(0).y = bloqueTam * 2+ offsetY;
			    structures.get(0).image = N4F3PA;
			    
			    structures.get(1).x = bloqueTam * 2 + offsetX;
			    structures.get(1).y = bloqueTam * 3+ offsetY;
			    structures.get(1).image = N4F3PA;
			    
			    structures.get(2).x = bloqueTam * 2 + offsetX;
			    structures.get(2).y = bloqueTam * 4+ offsetY;
			    structures.get(2).image = N4F3PA;
			    
			    structures.get(3).x = bloqueTam * 3 + offsetX;
			    structures.get(3).y = bloqueTam * 3+ offsetY;
			    structures.get(3).image = N4F3PA;
			    
			    structures.get(4).x = bloqueTam * 1 + offsetX;
			    structures.get(4).y = bloqueTam * 3+ offsetY;
			    structures.get(4).image = N4F3PA;
			    
			    structures.get(5).x = bloqueTam * 4 + offsetX;
			    structures.get(5).y = bloqueTam * 2+ offsetY;
			    structures.get(5).image = N4F3PA;
			    
			    structures.get(6).x = bloqueTam * 5 + offsetX;
			    structures.get(6).y = bloqueTam * 3+ offsetY;
			    structures.get(6).image = N4F3PA;
			    
			    structures.get(7).x = bloqueTam * 6 + offsetX;
			    structures.get(7).y = bloqueTam * 3+ offsetY;
			    structures.get(7).image = N4F3PA;
			    
			    structures.get(8).x = bloqueTam * 6 + offsetX;
			    structures.get(8).y = bloqueTam * 2+ offsetY;
			    structures.get(8).image = N4F3PA;
			    
			    structures.get(9).x = bloqueTam * 6 + offsetX;
			    structures.get(9).y = bloqueTam * 4+ offsetY;
			    structures.get(9).image = N4F3PA;
			    
			    structures.get(10).x = bloqueTam * 7 + offsetX;
			    structures.get(10).y = bloqueTam * 3+ offsetY;
			    structures.get(10).image = N4F3PA;
			    
			    structures.get(11).x = bloqueTam * 8 + offsetX;
			    structures.get(11).y = bloqueTam * 2+ offsetY;
			    structures.get(11).image = N4F3PA;
			    
			    structures.get(12).x = bloqueTam * 9 + offsetX;
			    structures.get(12).y = bloqueTam * 3+ offsetY;
			    structures.get(12).image = N4F3PA;
			    
			    structures.get(13).x = bloqueTam * 10 + offsetX;
			    structures.get(13).y = bloqueTam * 3+ offsetY;
			    structures.get(13).image = N4F3PA;
			    
			    structures.get(14).x = bloqueTam * 10 + offsetX;
			    structures.get(14).y = bloqueTam * 2+ offsetY;
			    structures.get(14).image = N4F3PA;
			    
			    structures.get(15).x = bloqueTam * 11 + offsetX;
			    structures.get(15).y = bloqueTam * 3+ offsetY;
			    structures.get(15).image = N4F3PA;
			   
			    structures.get(16).x = bloqueTam * 10 + offsetX;
			    structures.get(16).y = bloqueTam * 4+ offsetY;
			    structures.get(16).image = N4F3PA;
			    
			    structures.get(17).x = bloqueTam * 12 + offsetX;
			    structures.get(17).y = bloqueTam * 2+ offsetY;
			    structures.get(17).image = N4F3PA;
			   
			    structures.get(18).x = bloqueTam * 12 + offsetX;
			    structures.get(18).y = bloqueTam * 4+ offsetY;
			    structures.get(18).image = N4F3PA;
			   
			    structures.get(19).x = bloqueTam * 12 + offsetX;
			    structures.get(19).y = bloqueTam * 5+ offsetY;
			    structures.get(19).image = N4F3PA;
			    
			    structures.get(20).x = bloqueTam * 13 + offsetX;
			    structures.get(20).y = bloqueTam * 7+ offsetY;
			    structures.get(20).image = N4F3PA;
			    
			    structures.get(21).x = bloqueTam * 11 + offsetX;
			    structures.get(21).y = bloqueTam * 7+ offsetY;
			    structures.get(21).image = N4F3PA;
			    
			    structures.get(22).x = bloqueTam * 10 + offsetX;
			    structures.get(22).y = bloqueTam * 7+ offsetY;
			    structures.get(22).image = N4F3PA;
			    
			    structures.get(23).x = bloqueTam * 9 + offsetX;
			    structures.get(23).y = bloqueTam * 7+ offsetY;
			    structures.get(23).image = N4F3PA;
			    
			    structures.get(24).x = bloqueTam * 10 + offsetX;
			    structures.get(24).y = bloqueTam * 6 + offsetY;
			    structures.get(24).image = N4F3PA;
			    
			    structures.get(25).x = bloqueTam * 10 + offsetX;
			    structures.get(25).y = bloqueTam * 8 + offsetY;
			    structures.get(25).image = N4F3PA;

			    structures.get(26).x = bloqueTam * 8 + offsetX;
			    structures.get(26).y = bloqueTam * 6 + offsetY;
			    structures.get(26).image = N4F3PA;
			
			    structures.get(27).x = bloqueTam * 7 + offsetX;
			    structures.get(27).y = bloqueTam * 7 + offsetY;
			    structures.get(27).image = N4F3PA;
			    
			    structures.get(28).x = bloqueTam * 6 + offsetX;
			    structures.get(28).y = bloqueTam * 7 + offsetY;
			    structures.get(28).image = N4F3PA;
			    
			    structures.get(29).x = bloqueTam * 5 + offsetX;
			    structures.get(29).y = bloqueTam * 7 + offsetY;
			    structures.get(29).image = N4F3PA;
			    
			    structures.get(30).x = bloqueTam * 6 + offsetX;
			    structures.get(30).y = bloqueTam * 6 + offsetY;
			    structures.get(30).image = N4F3PA;
			    
			    structures.get(31).x = bloqueTam * 4 + offsetX;
			    structures.get(31).y = bloqueTam * 6 + offsetY;
			    structures.get(31).image = N4F3PA;
			    
			    structures.get(32).x = bloqueTam * 3 + offsetX;
			    structures.get(32).y = bloqueTam * 7 + offsetY;
			    structures.get(32).image = N4F3PA;
			    
			    structures.get(33).x = bloqueTam * 2 + offsetX;
			    structures.get(33).y = bloqueTam * 7 + offsetY;
			    structures.get(33).image = N4F3PA;
			    
			    structures.get(34).x = bloqueTam * 2 + offsetX;
			    structures.get(34).y = bloqueTam * 6 + offsetY;
			    structures.get(34).image = N4F3PA;
			    
			    structures.get(35).x = bloqueTam * 4 + offsetX;
			    structures.get(35).y = bloqueTam * 4 + offsetY;
			    structures.get(35).image = N4F3PA;
			    
			    structures.get(36).x = bloqueTam * 8 + offsetX;
			    structures.get(36).y = bloqueTam * 4 + offsetY;
			    structures.get(36).image = N4F3PA;
			    
			    
			    structures.get(37).x = bloqueTam * 3 + offsetX;
			    structures.get(37).y = bloqueTam * 1 + offsetY;
			    structures.get(37).image = cinta;
			    structures.get(37).tipo = 1;
			    
			    structures.get(38).x = bloqueTam * 3 + offsetX;
			    structures.get(38).y = bloqueTam * 2 + offsetY;
			    structures.get(38).image = cinta;
			    structures.get(38).tipo = 1;
			    
			    structures.get(39).x = bloqueTam * 4 + offsetX;
			    structures.get(39).y = bloqueTam * 1 + offsetY;
			    structures.get(39).image = cinta;
			    structures.get(39).tipo = 1;
			    
			    structures.get(40).x = bloqueTam * 5 + offsetX;
			    structures.get(40).y = bloqueTam * 2 + offsetY;
			    structures.get(40).image = cinta;
			    structures.get(40).tipo = 1;
			    
			    structures.get(41).x = bloqueTam * 7 + offsetX;
			    structures.get(41).y = bloqueTam * 2 + offsetY;
			    structures.get(41).image = cinta;
			    structures.get(41).tipo = 1;
			    
			    structures.get(42).x = bloqueTam * 10 + offsetX;
			    structures.get(42).y = bloqueTam * 1 + offsetY;
			    structures.get(42).image = cinta;
			    structures.get(42).tipo = 1;
			    
			    structures.get(43).x = bloqueTam * 11 + offsetX;
			    structures.get(43).y = bloqueTam * 2 + offsetY;
			    structures.get(43).image = cinta;
			    structures.get(43).tipo = 1;
			    
			    structures.get(44).x = bloqueTam * 13 + offsetX;
			    structures.get(44).y = bloqueTam * 2 + offsetY;
			    structures.get(44).image = cinta;
			    structures.get(44).tipo = 1;
			    
			    structures.get(45).x = bloqueTam * 3 + offsetX;
			    structures.get(45).y = bloqueTam * 4 + offsetY;
			    structures.get(45).image = cinta;
			    structures.get(45).tipo = 1;
			    
			    structures.get(46).x = bloqueTam * 4 + offsetX;
			    structures.get(46).y = bloqueTam * 5 + offsetY;
			    structures.get(46).image = cinta;
			    structures.get(46).tipo = 1;
			    
			    structures.get(47).x = bloqueTam * 7 + offsetX;
			    structures.get(47).y = bloqueTam * 4 + offsetY;
			    structures.get(47).image = cinta;
			    structures.get(47).tipo = 1;
			    
			    structures.get(48).x = bloqueTam * 9 + offsetX;
			    structures.get(48).y = bloqueTam * 5 + offsetY;
			    structures.get(48).image = cinta;
			    structures.get(48).tipo = 1;
			    
			    structures.get(49).x = bloqueTam * 11 + offsetX;
			    structures.get(49).y = bloqueTam * 6 + offsetY;
			    structures.get(49).image = cinta;
			    structures.get(49).tipo = 1;
			    
			    structures.get(50).x = bloqueTam * 12 + offsetX;
			    structures.get(50).y = bloqueTam * 6 + offsetY;
			    structures.get(50).image = cinta;
			    structures.get(50).tipo = 1;
			    
			    structures.get(51).x = bloqueTam * 1 + offsetX;
			    structures.get(51).y = bloqueTam * 6 + offsetY;
			    structures.get(51).image = cinta;
			    structures.get(51).tipo = 1;
			    
			    structures.get(52).x = bloqueTam * 3 + offsetX;
			    structures.get(52).y = bloqueTam * 6 + offsetY;
			    structures.get(52).image = cinta;
			    structures.get(52).tipo = 1;
			    
			    structures.get(53).x = bloqueTam * 5 + offsetX;
			    structures.get(53).y = bloqueTam * 6 + offsetY;
			    structures.get(53).image = cinta;
			    structures.get(53).tipo = 1;
			    
			    structures.get(54).x = bloqueTam * 7 + offsetX;
			    structures.get(54).y = bloqueTam * 6 + offsetY;
			    structures.get(54).image = cinta;
			    structures.get(54).tipo = 1;
			    
			    structures.get(55).x = bloqueTam * 5 + offsetX;
			    structures.get(55).y = bloqueTam * 8 + offsetY;
			    structures.get(55).image = cinta;
			    structures.get(55).tipo = 1;
			    
			    
			    
		} 
		
		// Condiciones para pasar de frame -------------------------------------------------------------
		if (Window.enemigosRestantes == 0 && Window.objetosRestantes == 0 && advanceFrame == 0) {
			advanceFrame = 1;
		    new Thread(() -> {
		        try {
		            Thread.sleep(2000); 
		        } catch (InterruptedException e) {
		            e.printStackTrace();
		        }

		        Window.frameSeleccionado++;
		        createObjects = 0;
		        advanceFrame = 0;
		        setRemainingProgressObjects = 0;
		        player.setX(bloqueTam * 1 + offsetX);
		        player.setY(bloqueTam * 1 + offsetY);
		        
		        for (Structure structures1 : structures) {
		            if (structures1.tipo == 1) {
		                structures1.bulletHits = 0;
		                structures1.setDestroyedFalse();
		                structures1.width = bloqueTam;
		                structures1.height = bloqueTam;
		            }
		        }
		        
		        Iterator<Item> iterator = items.iterator();
		        while (iterator.hasNext()) {
		            iterator.next(); 
		            iterator.remove();  
		        }
		        
		        for (Bomb bomb : player.bombs) { // Iterar sobre las bombas del jugador
		            bomb.stopBombSound();       // Llamar al método para detener el sonido del temporizador
		        }

		        // Limpiar la lista de bombas
		        player.bombs.clear();

		    }).start();
		}
		// Fin de Condiciones para pasar de frame -------------------------------------------------------------
		    
	}
	
	public void draw(Graphics g, int numColumnas, int numFilas, int bloqueTam, int offsetX, int offsetY, int frameSeleccionado, List<Structure> structures) {
		
		for (int i = 0; i < numColumnas; i++) {
	        for (int j = 0; j < numFilas; j++) {
	            int x = offsetX + i * bloqueTam;
	            int y = offsetY + j * bloqueTam;
	            
                if (i == 0) {
                    Window.limiteJugadorIzquierda = x + bloqueTam;  // Borde izquierdo
                }
                if (i == numColumnas - 1) {
                    Window.limiteJugadorDerecha = x - bloqueTam;  // Borde derecho
                }
                if (j == 0) {
                    Window.limiteJugadorArriba = y + bloqueTam;  // Borde superior
                }
                if (j == numFilas - 1) {
                    Window.limiteJugadorAbajo = y - bloqueTam;  // Borde inferior
                }
	            
            	if(frameSeleccionado == 1) {
            		            		
            		// Arriba a la izquierda
            		if(j == 0 && i == 0) {
		            	g.drawImage(N4F1ESQ_IZQ_SUP, x, y, bloqueTam, bloqueTam, null);
            		}
            		
            		// Arriba a la derecha
            		else if(j == 0 && i == numColumnas-1) {
		            	g.drawImage(N4F1ESQ_DER_SUP, x, y, bloqueTam, bloqueTam, null);
            		}
            		
            		// Abajo a la derecha
            		else if(j == numFilas-1 && i == numColumnas-1) {
		            	g.drawImage(N4F1ESQ_DER_INF, x, y, bloqueTam, bloqueTam, null);
            		}
            		
            		// Abajo a la izquierda
            		else if(j == numFilas-1 && i == 0) {
		            	g.drawImage(N4F1ESQ_IZQ_INF, x, y, bloqueTam, bloqueTam, null);
            		}
            		
            		// Bordes de arriba 
            		else if(j == 0) {
		            	g.drawImage(N4F1BORDE_ARRIBA, x, y, bloqueTam, bloqueTam, null);		            		
	            	}
	            	
            		// Bordes de abajo
	            	else if(j == numFilas - 1) {
		            	g.drawImage(N4F1BORDE_ABAJO, x, y, bloqueTam, bloqueTam, null);	
	            	}
	            	
            		// Bordes de la izquierda
	            	else if(i == 0) {
	            	    g.drawImage(N4F1BORDE_IZQ, x, y, bloqueTam, bloqueTam, null);
	    
	            	}
	            	
            		// Bordes de la derecha
	            	else if(i == numColumnas - 1) {
	            	    g.drawImage(N4F1BORDE_DER, x, y, bloqueTam, bloqueTam, null);
	            	}

	            		
            		// Suelo
	            	else {
	            		g.drawImage(N4F1SUELO, x, y, bloqueTam, bloqueTam, null);
	            	}
            	}
	            
	            	
	            	if(frameSeleccionado == 2) {
	            		// Arriba a la izquierda
	            		if(j == 0 && i == 0) {
			            	g.drawImage(N4F2ESQ_IZQ_SUP, x, y, bloqueTam, bloqueTam, null);
	            		}
	            		
	            		// Arriba a la derecha
	            		else if(j == 0 && i == numColumnas-1) {
			            	g.drawImage(N4F2ESQ_DER_SUP, x, y, bloqueTam, bloqueTam, null);
	            		}
	            		
	            		// Abajo a la derecha
	            		else if(j == numFilas-1 && i == numColumnas-1) {
			            	g.drawImage(N4F2ESQ_DER_INF, x, y, bloqueTam, bloqueTam, null);
	            		}
	            		
	            		// Abajo a la izquierda
	            		else if(j == numFilas-1 && i == 0) {
			            	g.drawImage(N4F2ESQ_IZQ_INF, x, y, bloqueTam, bloqueTam, null);
	            		}
	            		
	            		// Bordes de arriba 
	            		else if(j == 0) {
			            	g.drawImage(N4F2BORDE_ARRIBA, x, y, bloqueTam, bloqueTam, null);		            		
		            	}
		            	
	            		// Bordes de abajo
		            	else if(j == numFilas - 1) {
		            	    g.drawImage(N4F2BORDE_ABAJO, x, y, bloqueTam, bloqueTam, null);
		            	}
		            	
	            		// Bordes de la izquierda
		            	else if(i == 0) {
		            	    g.drawImage(N4F2BORDE_IZQ, x, y, bloqueTam, bloqueTam, null);
		            	}
		            	
	            		// Bordes de la derecha
		            	else if(i == numColumnas - 1) {  
		            	    g.drawImage(N4F2BORDE_DER, x, y, bloqueTam, bloqueTam, null);
		            	}

		            		
	            		// Suelo
		            	else {
		            		g.drawImage(N4F2SUELO, x, y, bloqueTam, bloqueTam, null);
		            	} 
	            	}
	            	
	            	
	            	if(frameSeleccionado == 3) {
	            		// Arriba a la izquierda
	            		if(j == 0 && i == 0) {
			            	g.drawImage(N4F3ESQ_IZQ_SUP, x, y, bloqueTam, bloqueTam, null);
	            		}
	            		
	            		// Arriba a la derecha
	            		else if(j == 0 && i == numColumnas-1) {
			            	g.drawImage(N4F3ESQ_DER_SUP, x, y, bloqueTam, bloqueTam, null);
	            		}
	            		
	            		// Abajo a la derecha
	            		else if(j == numFilas-1 && i == numColumnas-1) {
			            	g.drawImage(N4F3ESQ_DER_INF, x, y, bloqueTam, bloqueTam, null);
	            		}
	            		
	            		// Abajo a la izquierda
	            		else if(j == numFilas-1 && i == 0) {
			            	g.drawImage(N4F3ESQ_IZQ_INF, x, y, bloqueTam, bloqueTam, null);
	            		}
	            		
	            		// Bordes de arriba 
	            		else if(j == 0) {
			            	g.drawImage(N4F3BORDE_ARRIBA, x, y, bloqueTam, bloqueTam, null);		            		
		            	}
		            	
	            		// Bordes de abajo
		            	else if(j == numFilas - 1) {
		            	    g.drawImage(N4F3BORDE_ABAJO, x, y, bloqueTam, bloqueTam, null);
		            	}
		            	
	            		// Bordes de la izquierda
		            	else if(i == 0) {
		            	    g.drawImage(N4F3BORDE_IZQ, x, y, bloqueTam, bloqueTam, null);
		            	}
		            	
	            		// Bordes de la derecha
		            	else if(i == numColumnas - 1) {  
		            	    g.drawImage(N4F3BORDE_DER, x, y, bloqueTam, bloqueTam, null);
		            	}

		            		
	            		// Suelo
		            	else {
		            		g.drawImage(N4F3SUELO, x, y, bloqueTam, bloqueTam, null);
		            	} 
	            	}
	        }
	    }
	}
}

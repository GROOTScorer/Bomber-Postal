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

public class Level2 {

	
	// ----------------------- Declaracion de variables
	
	private int invalido = 0, setRemainingProgressObjects = 0, advanceFrame = 0, createObjects = 0;
    long lastPressedTime = 0;
    boolean canPress = true;
    private Image N2F1SUELO,
    N2F1BORDE_ABAJO,N2F1BORDE_ARRIBA,N2F1BORDE_DER,N2F1BORDE_IZQ,
    N2F1CAV, N2F1CAVO, N2F1CAG, N2F1CAA, N2F1CAR, N2F1ED1_1, N2F1ED1_2, N2F1ED1_3, N2F1ED1_4,
    N2F1ED2_1, N2F1ED2_2, N2F1ED2_3, N2F1ED2_4, N2F1ED2_5, N2F1ED3_1, N2F1ED3_2, N2F1ED3_3, N2F1ED3_4,
    N2F1ED4_1, N2F1ED4_2, N2F1ED5_1, N2F1ED5_2, N2F1ED7_1, N2F1ED7_2, N2F1ED7_3, N2F1ED9_1, N2F1ED9_3,
    N2F1ED9_4, N2F1ED9_5, coche, cocheh, N2F1ESQ_IZQ_INF, N2F1ESQ_IZQ_SUP, N2F1ESQ_DER_SUP, N2F1ESQ_DER_INF,
    
    N2F2SUELO, PLANTA1, PLANTA2, PLANTA3, PLANTA4, TIENDA1_1, TIENDA1_2, TIENDA2_1, TIENDA2_2, N2F2COLUMNA, cartel,
    escalera, bano,N2F2BORDE_ABAJO,N2F2BORDE_ARRIBA,N2F2BORDE_DER,N2F2BORDE_IZQ, N2F2ESQ_IZQ_INF, N2F2ESQ_IZQ_SUP, N2F2ESQ_DER_INF, N2F2ESQ_DER_SUP,
    
    N2F3ESQ_IZQ_INF, N2F3ESQ_IZQ_SUP, N2F3ESQ_DER_INF, N2F3ESQ_DER_SUP, N2F3SUELO, banca, N2F3BORDE_ABAJO,N2F3BORDE_ARRIBA,N2F3BORDE_DER,N2F3BORDE_IZQ;
    
	// ----------------------- Fin de Declaracion de variables
	
	// Constructor
	public Level2() {
        try {

            N2F1SUELO= ImageIO.read(new File("assets/images/SpritesBomberPostal/Mapa/Dia2/1_Calle/CalleCentro.png"));
            N2F1BORDE_ARRIBA= ImageIO.read(new File("assets/images/SpritesBomberPostal/Mapa/Dia2/1_Calle/BloqueBordeCiudad.png"));
            N2F1BORDE_ABAJO= ImageIO.read(new File("assets/images/SpritesBomberPostal/Mapa/Dia2/1_Calle/BloqueBordeCiudadInferior.png"));
            N2F1BORDE_IZQ= ImageIO.read(new File("assets/images/SpritesBomberPostal/Mapa/Dia2/1_Calle/BloqueBordeCiudadIzquierda.png"));
            N2F1BORDE_DER= ImageIO.read(new File("assets/images/SpritesBomberPostal/Mapa/Dia2/1_Calle/BloqueBordeCiudadDerecha.png"));
            
        	
            N2F1CAV = ImageIO.read(new File("assets/images/SpritesBomberPostal/Mapa/Dia2/1_Calle/CasVerdeLima.png"));
            N2F1CAVO = ImageIO.read(new File("assets/images/SpritesBomberPostal/Mapa/Dia2/1_Calle/CasaVerdeOscuro.png"));
            N2F1CAG = ImageIO.read(new File("assets/images/SpritesBomberPostal/Mapa/Dia2/1_Calle/CasaGris.png"));
            N2F1CAA = ImageIO.read(new File("assets/images/SpritesBomberPostal/Mapa/Dia2/1_Calle/CasaAzul.png"));
            N2F1CAR = ImageIO.read(new File("assets/images/SpritesBomberPostal/Mapa/Dia2/1_Calle/CasaRojo.png"));
            
            N2F1ED1_1 = ImageIO.read(new File("assets/images/SpritesBomberPostal/Mapa/Dia2/1_Calle/Edificio1_1.png"));
            N2F1ED1_2 = ImageIO.read(new File("assets/images/SpritesBomberPostal/Mapa/Dia2/1_Calle/Edificio1_2.png"));
            N2F1ED1_3 = ImageIO.read(new File("assets/images/SpritesBomberPostal/Mapa/Dia2/1_Calle/Edificio1_3.png"));
            N2F1ED1_4 = ImageIO.read(new File("assets/images/SpritesBomberPostal/Mapa/Dia2/1_Calle/Edificio1_4.png"));
            N2F1ED2_1 = ImageIO.read(new File("assets/images/SpritesBomberPostal/Mapa/Dia2/1_Calle/Edificio2_1.png"));
            N2F1ED2_2 = ImageIO.read(new File("assets/images/SpritesBomberPostal/Mapa/Dia2/1_Calle/Edificio2_2.png"));
            N2F1ED2_3 = ImageIO.read(new File("assets/images/SpritesBomberPostal/Mapa/Dia2/1_Calle/Edificio2_3.png"));
            N2F1ED2_4 = ImageIO.read(new File("assets/images/SpritesBomberPostal/Mapa/Dia2/1_Calle/Edificio2_4.png"));
            N2F1ED2_5 = ImageIO.read(new File("assets/images/SpritesBomberPostal/Mapa/Dia2/1_Calle/Edificio2_5.png"));
            N2F1ED3_1 = ImageIO.read(new File("assets/images/SpritesBomberPostal/Mapa/Dia2/1_Calle/Edificio3_1.png"));
            N2F1ED3_2 = ImageIO.read(new File("assets/images/SpritesBomberPostal/Mapa/Dia2/1_Calle/Edificio3_2.png"));
            N2F1ED3_3 = ImageIO.read(new File("assets/images/SpritesBomberPostal/Mapa/Dia2/1_Calle/Edificio3_3.png"));
            N2F1ED3_4 = ImageIO.read(new File("assets/images/SpritesBomberPostal/Mapa/Dia2/1_Calle/Edificio3_4.png"));
            N2F1ED4_1 = ImageIO.read(new File("assets/images/SpritesBomberPostal/Mapa/Dia2/1_Calle/Edificio4_1.png"));
            N2F1ED4_2 = ImageIO.read(new File("assets/images/SpritesBomberPostal/Mapa/Dia2/1_Calle/Edificio4_2.png"));
            N2F1ED5_1 = ImageIO.read(new File("assets/images/SpritesBomberPostal/Mapa/Dia2/1_Calle/Edificio5_1.png"));
            N2F1ED5_2 = ImageIO.read(new File("assets/images/SpritesBomberPostal/Mapa/Dia2/1_Calle/Edificio5_2.png"));
            N2F1ED7_1 = ImageIO.read(new File("assets/images/SpritesBomberPostal/Mapa/Dia2/1_Calle/Edificio7_1.png"));
            N2F1ED7_2  = ImageIO.read(new File("assets/images/SpritesBomberPostal/Mapa/Dia2/1_Calle/Edificio7_2.png"));
            N2F1ED7_3  = ImageIO.read(new File("assets/images/SpritesBomberPostal/Mapa/Dia2/1_Calle/Edificio7_3.png"));
            N2F1ED9_1 = ImageIO.read(new File("assets/images/SpritesBomberPostal/Mapa/Dia2/1_Calle/Edificio9_1.png"));
            N2F1ED9_3  = ImageIO.read(new File("assets/images/SpritesBomberPostal/Mapa/Dia2/1_Calle/Edificio9_3.png"));
            N2F1ED9_4  = ImageIO.read(new File("assets/images/SpritesBomberPostal/Mapa/Dia2/1_Calle/Edificio9_4.png"));
            N2F1ED9_5  = ImageIO.read(new File("assets/images/SpritesBomberPostal/Mapa/Dia2/1_Calle/Edificio9_5.png"));
            coche = ImageIO.read(new File("assets/images/SpritesBomberPostal/Mapa/Dia2/1_Calle/Coche.png"));
            cocheh = ImageIO.read(new File("assets/images/SpritesBomberPostal/Mapa/Dia2/1_Calle/CocheHorizontal.png"));
            N2F1ESQ_IZQ_INF = ImageIO.read(new File("assets/images/SpritesBomberPostal/Mapa/Dia2/1_Calle/BordeCiudadEsquinaInferiorIzquierda.png"));
            N2F1ESQ_IZQ_SUP  = ImageIO.read(new File("assets/images/SpritesBomberPostal/Mapa/Dia2/1_Calle/BordeCiudadEsquinaSuperiorIzquierda.png"));
            N2F1ESQ_DER_SUP = ImageIO.read(new File("assets/images/SpritesBomberPostal/Mapa/Dia2/1_Calle/BordeCiudadEsquinaSuperiorDerecha.png"));
            N2F1ESQ_DER_INF = ImageIO.read(new File("assets/images/SpritesBomberPostal/Mapa/Dia2/1_Calle/BordeCiudadEsquinaInferiorDerecha.png"));        
            
            
            
            N2F2BORDE_ARRIBA= ImageIO.read(new File("assets/images/SpritesBomberPostal/Mapa/Dia2/2_Shopping/BordeShooping.png"));
            N2F2BORDE_ABAJO= ImageIO.read(new File("assets/images/SpritesBomberPostal/Mapa/Dia2/2_Shopping/BordeShoopingInferior.png"));
            N2F2BORDE_IZQ= ImageIO.read(new File("assets/images/SpritesBomberPostal/Mapa/Dia2/2_Shopping/BordeShoopingIzquierda.png"));
            N2F2BORDE_DER= ImageIO.read(new File("assets/images/SpritesBomberPostal/Mapa/Dia2/2_Shopping/BordeShoopingDerecha.png"));
            N2F2SUELO = ImageIO.read(new File("assets/images/SpritesBomberPostal/Mapa/Dia2/2_Shopping/BloquePisoShooping.png"));
            
            N2F2ESQ_IZQ_INF = ImageIO.read(new File("assets/images/SpritesBomberPostal/Mapa/Dia2/2_Shopping/BordeShoopingEsquinaInferiorIzquierda.png"));
            N2F2ESQ_IZQ_SUP = ImageIO.read(new File("assets/images/SpritesBomberPostal/Mapa/Dia2/2_Shopping/BordeShoopingEsquinaSuperiorIzquierda.png"));
            N2F2ESQ_DER_INF = ImageIO.read(new File("assets/images/SpritesBomberPostal/Mapa/Dia2/2_Shopping/BordeShoopingEsquinaInferiorDerecha.png"));
            N2F2ESQ_DER_SUP = ImageIO.read(new File("assets/images/SpritesBomberPostal/Mapa/Dia2/2_Shopping/BordeShoopingEsquinaSuperiorDerecha.png"));
            
            
            PLANTA1 = ImageIO.read(new File("assets/images/SpritesBomberPostal/Mapa/Dia2/2_Shopping/Planta1.png"));
            PLANTA2 = ImageIO.read(new File("assets/images/SpritesBomberPostal/Mapa/Dia2/2_Shopping/Planta2.png"));
           PLANTA3 = ImageIO.read(new File("assets/images/SpritesBomberPostal/Mapa/Dia2/2_Shopping/Planta3.png"));
            PLANTA4 = ImageIO.read(new File("assets/images/SpritesBomberPostal/Mapa/Dia2/2_Shopping/Planta4.png"));

           TIENDA1_1 = ImageIO.read(new File("assets/images/SpritesBomberPostal/Mapa/Dia2/2_Shopping/Tienda1_1.png"));
            TIENDA1_2 = ImageIO.read(new File("assets/images/SpritesBomberPostal/Mapa/Dia2/2_Shopping/Tienda1_2.png"));
            TIENDA2_1 = ImageIO.read(new File("assets/images/SpritesBomberPostal/Mapa/Dia2/2_Shopping/Tienda2_1.png"));
            TIENDA2_2 = ImageIO.read(new File("assets/images/SpritesBomberPostal/Mapa/Dia2/2_Shopping/Tienda2_2.png"));
            N2F2COLUMNA = ImageIO.read(new File("assets/images/SpritesBomberPostal/Mapa/Dia2/2_Shopping/ColumnaShopping.png"));
            cartel = ImageIO.read(new File("assets/images/SpritesBomberPostal/Mapa/Dia2/2_Shopping/Cartel.png"));
            escalera = ImageIO.read(new File("assets/images/SpritesBomberPostal/Mapa/Dia2/2_Shopping/EscaleraMecanica.png"));
            bano = ImageIO.read(new File("assets/images/SpritesBomberPostal/Mapa/Dia2/2_Shopping/Baño.png"));
            
  
            
            
            
            
            
            N2F3BORDE_ARRIBA= ImageIO.read(new File("assets/images/SpritesBomberPostal/Mapa/Dia2/3_Iglesia/BordeIglesia.png"));
            N2F3BORDE_ABAJO= ImageIO.read(new File("assets/images/SpritesBomberPostal/Mapa/Dia2/3_Iglesia/BordeIglesiaInferior.png"));
            N2F3BORDE_IZQ= ImageIO.read(new File("assets/images/SpritesBomberPostal/Mapa/Dia2/3_Iglesia/BordeIglesiaIzquierda.png"));
            N2F3BORDE_DER= ImageIO.read(new File("assets/images/SpritesBomberPostal/Mapa/Dia2/3_Iglesia/BordeIglesiaDerecha.png"));
           
            N2F3ESQ_IZQ_INF = ImageIO.read(new File("assets/images/SpritesBomberPostal/Mapa/Dia2/3_Iglesia/BordeIglesiaEsquinaInferiorIzquierda.png"));
            N2F3ESQ_IZQ_SUP = ImageIO.read(new File("assets/images/SpritesBomberPostal/Mapa/Dia2/3_Iglesia/BordeIglesiaEsquinaSuperiorIzquierda.png"));
            N2F3ESQ_DER_INF = ImageIO.read(new File("assets/images/SpritesBomberPostal/Mapa/Dia2/3_Iglesia/BordeIglesiaEsquinaInferiorDerecha.png"));
            N2F3ESQ_DER_SUP = ImageIO.read(new File("assets/images/SpritesBomberPostal/Mapa/Dia2/3_Iglesia/BordeIglesiaEsquinaSuperiorDerecha.png"));
            N2F3SUELO = ImageIO.read(new File("assets/images/SpritesBomberPostal/Mapa/Dia2/3_Iglesia/PisoCeramica.png"));
            banca = ImageIO.read(new File("assets/images/SpritesBomberPostal/Mapa/Dia2/3_Iglesia/BancaIglesia.png"));
            
            
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public void update(List<Structure> structures, int bloqueTam, int offsetX, int offsetY, boolean iPressed, Player player, List<Power_Up> powerups, List<Innocent> inocentes, List<Police> policias, List<Military> militares, List<Item> items, List<FloatingText> floatingText,  List<Bomb> bombs) {			
		if(invalido != 1) {
			structures.add(new Structure(-10000, -10000 , bloqueTam, bloqueTam, N2F1CAV,0,powerups));
	    	 structures.add(new Structure(-10000, -10000 , bloqueTam, bloqueTam, N2F1CAV,0,powerups));
	    	 structures.add(new Structure(-10000, -10000 , bloqueTam, bloqueTam, N2F1CAV,0,powerups));
	    	 structures.add(new Structure(-10000, -10000 , bloqueTam, bloqueTam, N2F1CAV,0,powerups));
	    	 structures.add(new Structure(-10000, -10000 , bloqueTam, bloqueTam, N2F1CAV,0,powerups));
	    	 structures.add(new Structure(-10000, -10000 , bloqueTam, bloqueTam, N2F1CAV,0,powerups));
	    	 structures.add(new Structure(-10000, -10000 , bloqueTam, bloqueTam, N2F1CAV,0,powerups));
	    	 structures.add(new Structure(-10000, -10000 , bloqueTam, bloqueTam, N2F1CAV,0,powerups));
	    	 structures.add(new Structure(-10000, -10000 , bloqueTam, bloqueTam, N2F1CAV,0,powerups));
	    	 structures.add(new Structure(-10000, -10000 , bloqueTam, bloqueTam, N2F1CAV,0,powerups));
	    	 structures.add(new Structure(-10000, -10000 , bloqueTam, bloqueTam, N2F1CAV,0,powerups));
	    	 structures.add(new Structure(-10000, -10000 , bloqueTam, bloqueTam, N2F1CAV,0,powerups));
	    	 structures.add(new Structure(-10000, -10000 , bloqueTam, bloqueTam, N2F1CAV,0,powerups));
	    	 structures.add(new Structure(-10000, -10000 , bloqueTam, bloqueTam, N2F1CAV,0,powerups));
	    	 structures.add(new Structure(-10000, -10000 , bloqueTam, bloqueTam, N2F1CAV,0,powerups));
	    	 
	    	 
	    	 // FRAME 2
	    	 structures.add(new Structure(-10000, -10000 , bloqueTam, bloqueTam, N2F1CAV,0,powerups));
	    	 structures.add(new Structure(-10000, -10000 , bloqueTam, bloqueTam, N2F1CAV,0,powerups));
	    	 structures.add(new Structure(-10000, -10000 , bloqueTam, bloqueTam, N2F1CAV,0,powerups));
	    	 structures.add(new Structure(-10000, -10000 , bloqueTam, bloqueTam, N2F1CAV,0,powerups));
	    	 structures.add(new Structure(-10000, -10000 , bloqueTam, bloqueTam, N2F1CAV,0,powerups));
	    	 structures.add(new Structure(-10000, -10000 , bloqueTam, bloqueTam, N2F1CAV,0,powerups));
	    	 structures.add(new Structure(-10000, -10000 , bloqueTam, bloqueTam, N2F1CAV,0,powerups));
	    	 structures.add(new Structure(-10000, -10000 , bloqueTam, bloqueTam, N2F1CAV,0,powerups));
	    	 structures.add(new Structure(-10000, -10000 , bloqueTam, bloqueTam, N2F1CAV,0,powerups));
	    	 structures.add(new Structure(-10000, -10000 , bloqueTam, bloqueTam, N2F1CAV,0,powerups));
	    	 structures.add(new Structure(-10000, -10000 , bloqueTam, bloqueTam, N2F1CAV,0,powerups));
	    	 structures.add(new Structure(-10000, -10000 , bloqueTam, bloqueTam, N2F1CAV,0,powerups));
	    	 structures.add(new Structure(-10000, -10000 , bloqueTam, bloqueTam, N2F1CAV,0,powerups));
	    	 structures.add(new Structure(-10000, -10000 , bloqueTam, bloqueTam, N2F1CAV,0,powerups));
	    	 structures.add(new Structure(-10000, -10000 , bloqueTam, bloqueTam, N2F1CAV,0,powerups));


	    	 
	    	 // FRAME 3
	    	 
	    	 structures.add(new Structure(-10000, -10000 , bloqueTam, bloqueTam, N2F1CAV,0,powerups));
	    	 structures.add(new Structure(-10000, -10000 , bloqueTam, bloqueTam, N2F1CAV,0,powerups));
	    	 structures.add(new Structure(-10000, -10000 , bloqueTam, bloqueTam, N2F1CAV,0,powerups));
	    	 structures.add(new Structure(-10000, -10000 , bloqueTam, bloqueTam, N2F1CAV,0,powerups));
	    	 structures.add(new Structure(-10000, -10000 , bloqueTam, bloqueTam, N2F1CAV,0,powerups));
	    	 structures.add(new Structure(-10000, -10000 , bloqueTam, bloqueTam, N2F1CAV,0,powerups));
	    	 structures.add(new Structure(-10000, -10000 , bloqueTam, bloqueTam, N2F1CAV,0,powerups));
	    	 structures.add(new Structure(-10000, -10000 , bloqueTam, bloqueTam, N2F1CAV,0,powerups));
	    	 structures.add(new Structure(-10000, -10000 , bloqueTam, bloqueTam, N2F1CAV,0,powerups));
	    	 structures.add(new Structure(-10000, -10000 , bloqueTam, bloqueTam, N2F1CAV,0,powerups));
	    	 structures.add(new Structure(-10000, -10000 , bloqueTam, bloqueTam, N2F1CAV,0,powerups));
	    	 structures.add(new Structure(-10000, -10000 , bloqueTam, bloqueTam, N2F1CAV,0,powerups));
	    	 structures.add(new Structure(-10000, -10000 , bloqueTam, bloqueTam, N2F1CAV,0,powerups));
	    	 structures.add(new Structure(-10000, -10000 , bloqueTam, bloqueTam, N2F1CAV,0,powerups));
	    	 structures.add(new Structure(-10000, -10000 , bloqueTam, bloqueTam, N2F1CAV,0,powerups));
	    	 
	    	 structures.add(new Structure(-10000, -10000 , bloqueTam, bloqueTam, N2F1CAV,0,powerups));
	    	 structures.add(new Structure(-10000, -10000 , bloqueTam, bloqueTam, N2F1CAV,0,powerups));
	    	 structures.add(new Structure(-10000, -10000 , bloqueTam, bloqueTam, N2F1CAV,0,powerups));
	    	 structures.add(new Structure(-10000, -10000 , bloqueTam, bloqueTam, N2F1CAV,0,powerups));
	    	 structures.add(new Structure(-10000, -10000 , bloqueTam, bloqueTam, N2F1CAV,0,powerups));
	    	 structures.add(new Structure(-10000, -10000 , bloqueTam, bloqueTam, N2F1CAV,0,powerups));
	    	 structures.add(new Structure(-10000, -10000 , bloqueTam, bloqueTam, N2F1CAV,0,powerups));
	    	 structures.add(new Structure(-10000, -10000 , bloqueTam, bloqueTam, N2F1CAV,0,powerups));
	    	 structures.add(new Structure(-10000, -10000 , bloqueTam, bloqueTam, N2F1CAV,0,powerups));
	    	 structures.add(new Structure(-10000, -10000 , bloqueTam, bloqueTam, N2F1CAV,0,powerups));
	    	 structures.add(new Structure(-10000, -10000 , bloqueTam, bloqueTam, N2F1CAV,0,powerups));
	    	 structures.add(new Structure(-10000, -10000 , bloqueTam, bloqueTam, N2F1CAV,0,powerups));
	    	 structures.add(new Structure(-10000, -10000 , bloqueTam, bloqueTam, N2F1CAV,0,powerups));
	    	 structures.add(new Structure(-10000, -10000 , bloqueTam, bloqueTam, N2F1CAV,0,powerups));
	    	 structures.add(new Structure(-10000, -10000 , bloqueTam, bloqueTam, N2F1CAV,0,powerups));
	    	 

	    	 structures.add(new Structure(-10000, -10000 , bloqueTam, bloqueTam, N2F1CAV,0,powerups));
	    	 structures.add(new Structure(-10000, -10000 , bloqueTam, bloqueTam, N2F1CAV,0,powerups));
	    	 structures.add(new Structure(-10000, -10000 , bloqueTam, bloqueTam, N2F1CAV,0,powerups));
	    	 structures.add(new Structure(-10000, -10000 , bloqueTam, bloqueTam, N2F1CAV,0,powerups));
	    	 structures.add(new Structure(-10000, -10000 , bloqueTam, bloqueTam, N2F1CAV,0,powerups));
	    	 structures.add(new Structure(-10000, -10000 , bloqueTam, bloqueTam, N2F1CAV,0,powerups));
	    	 structures.add(new Structure(-10000, -10000 , bloqueTam, bloqueTam, N2F1CAV,0,powerups));
	    	 structures.add(new Structure(-10000, -10000 , bloqueTam, bloqueTam, N2F1CAV,0,powerups));
	    	 structures.add(new Structure(-10000, -10000 , bloqueTam, bloqueTam, N2F1CAV,0,powerups));
	    	 structures.add(new Structure(-10000, -10000 , bloqueTam, bloqueTam, N2F1CAV,0,powerups));
	    	 structures.add(new Structure(-10000, -10000 , bloqueTam, bloqueTam, N2F1CAV,0,powerups));
	    	 structures.add(new Structure(-10000, -10000 , bloqueTam, bloqueTam, N2F1CAV,0,powerups));
	    	 structures.add(new Structure(-10000, -10000 , bloqueTam, bloqueTam, N2F1CAV,0,powerups));
	    	 structures.add(new Structure(-10000, -10000 , bloqueTam, bloqueTam, N2F1CAV,0,powerups));
	    	 structures.add(new Structure(-10000, -10000 , bloqueTam, bloqueTam, N2F1CAV,0,powerups));
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
			// Se instancia 1 vez por nivel a los enemigos
			if(createObjects == 0) {
				policias.add(new Police(bloqueTam * 3 + offsetX, bloqueTam * 4+ offsetY , bloqueTam, bloqueTam));
				policias.add(new Police(bloqueTam * 1 + offsetX, bloqueTam * 5+ offsetY , bloqueTam, bloqueTam));
				policias.add(new Police(bloqueTam * 9 + offsetX, bloqueTam * 2+ offsetY , bloqueTam, bloqueTam));
				
		    	 inocentes.add(new Innocent(bloqueTam * 3 + offsetX, bloqueTam * 8+ offsetY , bloqueTam, bloqueTam));
		    	 inocentes.add(new Innocent(bloqueTam * 8 + offsetX, bloqueTam * 8+ offsetY , bloqueTam, bloqueTam));
		    	 inocentes.add(new Innocent(bloqueTam * 5 + offsetX, bloqueTam * 5+ offsetY , bloqueTam, bloqueTam));
		    	 
		    	 items.add(new Item(bloqueTam * 6 + offsetX, bloqueTam * 1+ offsetY , bloqueTam, bloqueTam, player,2));
		    	 items.add(new Item(bloqueTam * 12 + offsetX, bloqueTam * 1+ offsetY , bloqueTam, bloqueTam, player,2));
		    	 items.add(new Item(bloqueTam * 4 + offsetX, bloqueTam * 6+ offsetY , bloqueTam, bloqueTam, player,2));
		    	 items.add(new Item(bloqueTam * 12 + offsetX, bloqueTam * 5+ offsetY , bloqueTam, bloqueTam, player,2));
		    	 
		    	 items.add(new Item(bloqueTam * 13 + offsetX, bloqueTam * 8+ offsetY , bloqueTam, bloqueTam, player,1));
		    	 items.add(new Item(bloqueTam * 13 + offsetX, bloqueTam * 2+ offsetY , bloqueTam, bloqueTam, player,1));

		    	 
		    	 
				
				
				
				
				
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
			 structures.get(0).y = bloqueTam * 2 + offsetY;
			 structures.get(0).image = N2F1ED7_2;
			 
			 structures.get(1).x = bloqueTam * 2 + offsetX; // ED
			 structures.get(1).y = bloqueTam * 3 + offsetY;
			 structures.get(1).image = N2F1ED7_1;
			 
			 structures.get(2).x = bloqueTam * 3 + offsetX;
			 structures.get(2).y = bloqueTam * 2 + offsetY;
			 structures.get(2).image = N2F1ED7_3;
			
			 structures.get(3).x = bloqueTam * 5 + offsetX;
			 structures.get(3).y = bloqueTam * 2 + offsetY;
			 structures.get(3).image = N2F1ED2_5;
			 
			 structures.get(4).x = bloqueTam * 5 + offsetX;
			 structures.get(4).y = bloqueTam * 3 + offsetY;
			 structures.get(4).image = N2F1ED2_4;
			
			 structures.get(5).x = bloqueTam * 5 + offsetX;
			 structures.get(5).y = bloqueTam * 4 + offsetY;
			 structures.get(5).image = N2F1ED2_1;
			 
			 structures.get(6).x = bloqueTam * 4 + offsetX;
			 structures.get(6).y = bloqueTam * 4 + offsetY;
			 structures.get(6).image = N2F1ED2_2;
			 
			 structures.get(7).x = bloqueTam * 6 + offsetX;
			 structures.get(7).y = bloqueTam * 4 + offsetY;
			 structures.get(7).image = N2F1ED2_3;
			 
			 structures.get(8).x = bloqueTam * 7 + offsetX;
			 structures.get(8).y = bloqueTam * 2 + offsetY;
			 structures.get(8).image = N2F1ED5_1;
			 
			 structures.get(9).x = bloqueTam * 8 + offsetX;
			 structures.get(9).y = bloqueTam * 2 + offsetY;
			 structures.get(9).image = N2F1ED5_2;
			 
			 structures.get(10).x = bloqueTam * 9 + offsetX;
			 structures.get(10).y = bloqueTam * 1 + offsetY;
			 structures.get(10).image = N2F1CAVO;
			 
			 structures.get(11).x = bloqueTam * 10 + offsetX;
			 structures.get(11).y = bloqueTam * 3 + offsetY;
			 structures.get(11).image = N2F1ED9_1;
			 
			 structures.get(12).x = bloqueTam * 11 + offsetX;
			 structures.get(12).y = bloqueTam * 3 + offsetY;
			 structures.get(12).image = N2F1ED9_3;
			 
			 structures.get(13).x = bloqueTam * 11 + offsetX;
			 structures.get(13).y = bloqueTam * 2 + offsetY;
			 structures.get(13).image = N2F1ED9_5;
			 
			
			 
			 structures.get(14).x = bloqueTam * 12 + offsetX;
			 structures.get(14).y = bloqueTam * 3 + offsetY;
			 structures.get(14).image = N2F1ED9_4;
			
			 
			 
			 
			 structures.get(15).x = bloqueTam * 2 + offsetX;	
			 structures.get(15).y = bloqueTam * 7 + offsetY;
			 structures.get(15).image = N2F1CAR;
			 
			 
			 structures.get(16).x = bloqueTam * 4 + offsetX;
			 structures.get(16).y = bloqueTam * 7 + offsetY;
			 structures.get(16).image = N2F1CAG;
			
			 structures.get(17).x = bloqueTam * 6 + offsetX;  
			 structures.get(17).y = bloqueTam * 7 + offsetY;      
			 structures.get(17).image = N2F1ED1_1;         
			 
			 structures.get(18).x = bloqueTam * 8 + offsetX;  
			 structures.get(18).y = bloqueTam * 7 + offsetY;      
			 structures.get(18).image = N2F1CAG; 
			 
			 structures.get(19).x = bloqueTam * 10 + offsetX;  
			 structures.get(19).y = bloqueTam * 7 + offsetY;      
			 structures.get(19).image = N2F1CAVO; 
			 
			 structures.get(21).x = bloqueTam * 12 + offsetX;  
			 structures.get(21).y = bloqueTam * 7 + offsetY;      
			 structures.get(21).image = N2F1CAV; 
			 
			 //
			 
			 structures.get(22).x = bloqueTam * 5 + offsetX;  
			 structures.get(22).y = bloqueTam * 6 + offsetY;      
			 structures.get(22).image = N2F1ED1_4; 
			 
			 structures.get(23).x = bloqueTam * 6 + offsetX;  
			 structures.get(23).y = bloqueTam * 6 + offsetY;      
			 structures.get(23).image = N2F1ED1_2; 
			 
			 structures.get(24).x = bloqueTam * 7 + offsetX;  
			 structures.get(24).y = bloqueTam * 6 + offsetY;      
			 structures.get(24).image = N2F1ED1_3; 
			 
			 structures.get(25).x = bloqueTam * 8 + offsetX;  
			 structures.get(25).y = bloqueTam * 5 + offsetY;      
			 structures.get(25).image = N2F1ED3_2; 
			 
			 structures.get(26).x = bloqueTam * 9 + offsetX;  
			 structures.get(26).y = bloqueTam * 5 + offsetY;      
			 structures.get(26).image = N2F1ED3_1; 
			 
			 structures.get(27).x = bloqueTam * 9 + offsetX;  
			 structures.get(27).y = bloqueTam * 4 + offsetY;      
			 structures.get(27).image = N2F1ED3_4; 
			 
			 structures.get(28).x = bloqueTam * 8 + offsetX;  
			 structures.get(28).y = bloqueTam * 4 + offsetY;      
			 structures.get(28).image = N2F1ED3_3; 
			 
			 
			 
			 
			
			 
			 
			 structures.get(30).x = bloqueTam * 2 + offsetX;  // poster
			 structures.get(30).y = bloqueTam * 4 + offsetY;      
			 structures.get(30).tipo = 1;
			 structures.get(30).image = coche ; 
			 
			 //Objetos
			 
			 structures.get(31).x = bloqueTam * 10 + offsetX;  //poster
			 structures.get(31).y = bloqueTam * 4 + offsetY;      
			 structures.get(31).image = coche ; 
			 structures.get(31).tipo = 1;
			 
			 structures.get(32).x = bloqueTam * 12 + offsetX;  //poster
			 structures.get(32).y = bloqueTam * 4 + offsetY;      
			 structures.get(32).image = coche ; 
			 structures.get(32).tipo = 1;
			 
			 structures.get(33).x = bloqueTam * 12 + offsetX;  //poster
			 structures.get(33).y = bloqueTam * 2 + offsetY;      
			 structures.get(33).image = coche ; 
			 structures.get(33).tipo = 1;
			 
			 
			 structures.get(34).x = bloqueTam * 3 + offsetX;  //
			 structures.get(34).y = bloqueTam * 1 + offsetY;      
			 structures.get(34).image = cocheh ; 
			 structures.get(34).tipo = 1;
			 
			 structures.get(35).x = bloqueTam * 6 + offsetX;  
			 structures.get(35).y = bloqueTam * 2 + offsetY;      
			 structures.get(35).image = coche ; 
			 structures.get(35).tipo = 1;
			 
			 
			 structures.get(36).x = bloqueTam * 1 + offsetX;  
			 structures.get(36).y = bloqueTam * 3 + offsetY;      
			 structures.get(36).image = coche ; 
			 structures.get(36).tipo = 1;
			 
			 structures.get(37).x = bloqueTam * 7 + offsetX;  //
			 structures.get(37).y = bloqueTam * 1 + offsetY;      
			 structures.get(37).image = cocheh ; 
			 structures.get(37).tipo = 1;
			 
			 
			 structures.get(38).x = bloqueTam * 1 + offsetX;  
			 structures.get(38).y = bloqueTam * 6 + offsetY;      
			 structures.get(38).image = coche ; 
			 structures.get(38).tipo = 1;
			 
			 structures.get(39).x = bloqueTam * 13 + offsetX;  
			 structures.get(39).y = bloqueTam * 1 + offsetY;      
			 structures.get(39).image = N2F1CAA ; 
			 
			 structures.get(40).x = bloqueTam * 8 + offsetX;  
			 structures.get(40).y = bloqueTam * 3 + offsetY;      
			 structures.get(40).image = cocheh ; 
			 structures.get(40).tipo = 1;
			 
			 structures.get(41).x = bloqueTam * 10 + offsetX;  //poster
			 structures.get(41).y = bloqueTam * 2 + offsetY;      
			 structures.get(41).image = coche ; 
			 structures.get(41).tipo = 1;
			 
			 structures.get(42).x = bloqueTam * 7 + offsetX;  
			 structures.get(42).y = bloqueTam * 5 + offsetY;      
			 structures.get(42).image = coche ; 
			 structures.get(42).tipo = 1;
			 
			 
			 structures.get(43).x = bloqueTam * 4 + offsetX;  
			 structures.get(43).y = bloqueTam * 5 + offsetY;      
			 structures.get(43).image = coche ; 
			 structures.get(43).tipo = 1;
			 
			 structures.get(44).x = bloqueTam * 4 + offsetX;  
			 structures.get(44).y = bloqueTam * 3 + offsetY;      
			 structures.get(44).image = coche ; 
			 structures.get(44).tipo = 1;
			 
			 structures.get(45).x = bloqueTam * 5 + offsetX;  //
			 structures.get(45).y = bloqueTam * 1 + offsetY;      
			 structures.get(45).image = cocheh ; 
			 structures.get(45).tipo = 1;
			 
			 
			 structures.get(46).x = bloqueTam * 11 + offsetX;  
			 structures.get(46).y = bloqueTam * 5 + offsetY;      
			 structures.get(46).image = N2F1CAG ; 
			 
			 structures.get(47).x = bloqueTam * 13 + offsetX;  
			 structures.get(47).y = bloqueTam * 5 + offsetY;      
			 structures.get(47).image = N2F1ED4_2; 
			 
			 structures.get(48).x = bloqueTam * 3 + offsetX;  //
			 structures.get(48).y = bloqueTam * 7 + offsetY;      
			 structures.get(48).image = cocheh ; 
			 structures.get(48).tipo = 1;
			 
			 structures.get(49).x = bloqueTam * 5 + offsetX; //  
			 structures.get(49).y = bloqueTam * 7 + offsetY;      
			 structures.get(49).image = cocheh ; 
			 structures.get(49).tipo = 1;
			 
			 structures.get(50).x = bloqueTam * 7 + offsetX;   //
			 structures.get(50).y = bloqueTam * 7 + offsetY;      
			 structures.get(50).image = cocheh ; 
			 structures.get(50).tipo = 1;
			 
			 structures.get(51).x = bloqueTam * 9 + offsetX;   //
			 structures.get(51).y = bloqueTam * 7 + offsetY;      
			 structures.get(51).image = cocheh ; 
			 structures.get(51).tipo = 1;
			 
			 structures.get(52).x = bloqueTam * 11 + offsetX;  
			 structures.get(52).y = bloqueTam * 7 + offsetY;      //
			 structures.get(52).image = cocheh; 
			 structures.get(52).tipo = 1;
			 
			 
			 structures.get(53).x = bloqueTam * 13 + offsetX;  //
			 structures.get(53).y = bloqueTam * 7 + offsetY;      
			 structures.get(53).image = cocheh ; 
			 structures.get(53).tipo = 1;
			 
			 structures.get(54).x = bloqueTam * 13 + offsetX;  
			 structures.get(54).y = bloqueTam * 6 + offsetY;      
			 structures.get(54).image = N2F1ED4_1 ; 
			 
			 structures.get(55).x = bloqueTam * 2 + offsetX;  
			 structures.get(55).y = bloqueTam * 5 + offsetY;      
			 structures.get(55).image = N2F1ED5_1 ; 
			 
			 structures.get(56).x = bloqueTam * 3 + offsetX;  
			 structures.get(56).y = bloqueTam * 5 + offsetY;      
			 structures.get(56).image = N2F1ED5_2 ;
		}   
		    
		else if(Window.frameSeleccionado == 2) {

			if(createObjects == 0) {
				
				policias.add(new Police(bloqueTam * 5 + offsetX, bloqueTam * 8+ offsetY , bloqueTam, bloqueTam));
				policias.add(new Police(bloqueTam * 5 + offsetX, bloqueTam * 3+ offsetY , bloqueTam, bloqueTam));
				policias.add(new Police(bloqueTam * 11 + offsetX, bloqueTam * 6+ offsetY , bloqueTam, bloqueTam));
				policias.add(new Police(bloqueTam * 13 + offsetX, bloqueTam * 6+ offsetY , bloqueTam, bloqueTam));

				
				
		    	 inocentes.add(new Innocent(bloqueTam * 10 + offsetX, bloqueTam * 1 + offsetY , bloqueTam, bloqueTam));
		    	 inocentes.add(new Innocent(bloqueTam * 6 + offsetX, bloqueTam * 5 + offsetY , bloqueTam, bloqueTam));
		    	 inocentes.add(new Innocent(bloqueTam * 9 + offsetX, bloqueTam * 8 + offsetY , bloqueTam, bloqueTam));

		    	 
		    	
		    	 items.add(new Item(bloqueTam * 3 + offsetX, bloqueTam * 1+ offsetY , bloqueTam, bloqueTam, player,2));
		    	 items.add(new Item(bloqueTam * 10 + offsetX, bloqueTam * 3+ offsetY , bloqueTam, bloqueTam, player,2));
		    	 items.add(new Item(bloqueTam * 7 + offsetX, bloqueTam * 8+ offsetY , bloqueTam, bloqueTam, player,2));
		    	 items.add(new Item(bloqueTam * 6 + offsetX, bloqueTam * 2+ offsetY , bloqueTam, bloqueTam, player,2));

		    	
		    	 items.add(new Item(bloqueTam * 13 + offsetX, bloqueTam * 8+ offsetY , bloqueTam, bloqueTam, player,1));

		    
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
		    structures.get(0).image = N2F2COLUMNA;
		    
		    structures.get(1).x = bloqueTam * 2 + offsetX;
		    structures.get(1).y = bloqueTam * 3+ offsetY;
		    structures.get(1).image = N2F2COLUMNA;
		    
		    structures.get(2).x = bloqueTam * 2 + offsetX;
		    structures.get(2).y = bloqueTam * 5+ offsetY;
		    structures.get(2).image = N2F2COLUMNA;
		   
		    structures.get(3).x = bloqueTam * 2 + offsetX;
		    structures.get(3).y = bloqueTam * 7+ offsetY;
		    structures.get(3).image = N2F2COLUMNA;
		    
		    structures.get(4).x = bloqueTam * 12 + offsetX;
		    structures.get(4).y = bloqueTam * 2 + offsetY;
		    structures.get(4).image = N2F2COLUMNA;
		    
		    structures.get(5).x = bloqueTam * 12 + offsetX;
		    structures.get(5).y = bloqueTam * 4+ offsetY;
		    structures.get(5).image = N2F2COLUMNA;
		    
		    structures.get(6).x = bloqueTam * 12 + offsetX;
		    structures.get(6).y = bloqueTam * 6+ offsetY;
		    structures.get(6).image = N2F2COLUMNA;
		    
		    structures.get(7).x = bloqueTam * 12 + offsetX;
		    structures.get(7).y = bloqueTam * 8+ offsetY;
		    structures.get(7).image = N2F2COLUMNA;
		    
		    structures.get(8).x = bloqueTam * 4 + offsetX;
		    structures.get(8).y = bloqueTam * 7+ offsetY;
		    structures.get(8).image = TIENDA2_1;
		    
		    structures.get(9).x = bloqueTam * 5 + offsetX;
		    structures.get(9).y = bloqueTam * 7+ offsetY;
		    structures.get(9).image = TIENDA2_2;
		    
		    structures.get(10).x = bloqueTam * 7 + offsetX;
		    structures.get(10).y = bloqueTam * 7+ offsetY;
		    structures.get(10).image = bano;
		    
		    structures.get(11).x = bloqueTam * 8 + offsetX;
		    structures.get(11).y = bloqueTam * 8+ offsetY;
		    structures.get(11).image = cartel;
		    structures.get(11).tipo = 1;
		    
		    structures.get(12).x = bloqueTam * 9 + offsetX;
		    structures.get(12).y = bloqueTam * 7+ offsetY;
		    structures.get(12).image = TIENDA1_1;
		    
		    structures.get(13).x = bloqueTam * 10 + offsetX;
		    structures.get(13).y = bloqueTam * 7+ offsetY;
		    structures.get(13).image = TIENDA1_2;
		    
		    structures.get(14).x = bloqueTam * 4 + offsetX;
		    structures.get(14).y = bloqueTam * 2+ offsetY;
		    structures.get(14).image = TIENDA1_1;
		    
		    structures.get(15).x = bloqueTam * 5 + offsetX;
		    structures.get(15).y = bloqueTam * 2+ offsetY;
		    structures.get(15).image = TIENDA1_2;
		    
		    structures.get(16).x = bloqueTam * 6 + offsetX;
		    structures.get(16).y = bloqueTam * 1+ offsetY;
		    structures.get(16).image = cartel;
		    structures.get(16).tipo = 1;

		    
		    structures.get(17).x = bloqueTam * 7 + offsetX;
		    structures.get(17).y = bloqueTam * 2+ offsetY;
		    structures.get(17).image = bano;
		    
		    structures.get(18).x = bloqueTam * 9 + offsetX;
		    structures.get(18).y = bloqueTam * 2+ offsetY;
		    structures.get(18).image = TIENDA2_1;
		    
		    structures.get(19).x = bloqueTam * 10 + offsetX;
		    structures.get(19).y = bloqueTam * 2+ offsetY;
		    structures.get(19).image = TIENDA2_2;
		    
		    structures.get(20).x = bloqueTam * 4 + offsetX;
		    structures.get(20).y = bloqueTam * 4+ offsetY;
		    structures.get(20).image = PLANTA1;
		    
		    structures.get(21).x = bloqueTam * 5 + offsetX;
		    structures.get(21).y = bloqueTam * 4+ offsetY;
		    structures.get(21).image = PLANTA2;
		    
		    structures.get(22).x = bloqueTam * 4 + offsetX;
		    structures.get(22).y = bloqueTam * 5+ offsetY;
		    structures.get(22).image = PLANTA3;
		    
		    structures.get(23).x = bloqueTam * 5 + offsetX;
		    structures.get(23).y = bloqueTam * 5+ offsetY;
		    structures.get(23).image = PLANTA4;
		    
		    structures.get(24).x = bloqueTam * 7 + offsetX;
		    structures.get(24).y = bloqueTam * 5+ offsetY;
		    structures.get(24).image = escalera;
		    
		    structures.get(25).x = bloqueTam * 7 + offsetX;
		    structures.get(25).y = bloqueTam * 4+ offsetY;
		    structures.get(25).image = escalera;
		    
		    structures.get(26).x = bloqueTam * 9 + offsetX;
		    structures.get(26).y = bloqueTam * 4+ offsetY;
		    structures.get(26).image = PLANTA1;
		    
		    structures.get(27).x = bloqueTam * 10 + offsetX;
		    structures.get(27).y = bloqueTam * 4+ offsetY;
		    structures.get(27).image = PLANTA2;
		    
		    structures.get(28).x = bloqueTam * 9 + offsetX;
		    structures.get(28).y = bloqueTam * 5+ offsetY;
		    structures.get(28).image = PLANTA3;
		    
		    structures.get(29).x = bloqueTam * 10 + offsetX;
		    structures.get(29).y = bloqueTam * 5+ offsetY;
		    structures.get(29).image = PLANTA4;
		    
		    structures.get(30).x = bloqueTam * 2 + offsetX;
		    structures.get(30).y = bloqueTam * 2+ offsetY;
		    structures.get(30).image = cartel;
		    structures.get(30).tipo = 1;
		    
		    structures.get(31).x = bloqueTam * 3 + offsetX;
		    structures.get(31).y = bloqueTam * 2+ offsetY;
		    structures.get(31).image = cartel;
		    structures.get(31).tipo = 1;
		    
		    structures.get(32).x = bloqueTam * 1 + offsetX;
		    structures.get(32).y = bloqueTam * 4+ offsetY;
		    structures.get(32).image = cartel;
		    structures.get(32).tipo = 1;
		    
		    structures.get(33).x = bloqueTam * 2 + offsetX;
		    structures.get(33).y = bloqueTam * 4+ offsetY;
		    structures.get(33).image = cartel;
		    structures.get(33).tipo = 1;
		    
		    
		    structures.get(34).x = -10000;
		    structures.get(34).y = -10000;
		    structures.get(34).image = cartel;
		    structures.get(34).tipo = 1;
		    
		    
		    structures.get(35).x = bloqueTam * 1 + offsetX;
		    structures.get(35).y = bloqueTam * 7 + offsetY;
		    structures.get(35).image = cartel;
		    structures.get(35).tipo = 1;
		    
		    structures.get(36).x = bloqueTam * 1 + offsetX;
		    structures.get(36).y = bloqueTam * 8 + offsetY;
		    structures.get(36).image = cartel;
		    structures.get(36).tipo = 1;
		    
		    structures.get(37).x = bloqueTam * 2 + offsetX;
		    structures.get(37).y = bloqueTam * 8 + offsetY;
		    structures.get(37).image = cartel;
		    structures.get(37).tipo = 1;
		    
		    
		    structures.get(38).x = bloqueTam  -10000 + offsetX;
		    structures.get(38).y = bloqueTam  -10000 + offsetY;
		    structures.get(38).image = cartel;
		    structures.get(38).tipo = 1;
		    
		    structures.get(39).x = bloqueTam  -10000 + offsetX;
		    structures.get(39).y = bloqueTam -10000 + offsetY;
		    structures.get(39).image = cartel;
		    structures.get(39).tipo = 1;
		    
		    structures.get(40).x = bloqueTam * 8 + offsetX;
		    structures.get(40).y = bloqueTam * 1 + offsetY;
		    structures.get(40).image = cartel;
		    structures.get(40).tipo = 1;
		    
		    structures.get(41).x = bloqueTam * 6 + offsetX;
		    structures.get(41).y = bloqueTam * 3 + offsetY;
		    structures.get(41).image = cartel;
		    structures.get(41).tipo = 1;
		    
		    structures.get(42).x = bloqueTam * 3 + offsetX;
		    structures.get(42).y = bloqueTam * 6 + offsetY;
		    structures.get(42).image = cartel;
		    structures.get(42).tipo = 1;
		    
		    structures.get(43).x = bloqueTam * 6 + offsetX;
		    structures.get(43).y = bloqueTam * 7 + offsetY;
		    structures.get(43).image = cartel;
		    structures.get(43).tipo = 1;
		    
		    structures.get(44).x = bloqueTam * 6 + offsetX;
		    structures.get(44).y = bloqueTam * 8 + offsetY;
		    structures.get(44).image = cartel;
		    structures.get(44).tipo = 1;
		    
		    structures.get(45).x = bloqueTam * 11 + offsetX;
		    structures.get(45).y = bloqueTam * 8 + offsetY;
		    structures.get(45).image = cartel;
		    structures.get(45).tipo = 1;
		    
		    structures.get(46).x = bloqueTam * 13 + offsetX;
		    structures.get(46).y = bloqueTam * 7 + offsetY;
		    structures.get(46).image = cartel;
		    structures.get(46).tipo = 1;
		    
		    structures.get(47).x = bloqueTam * 8 + offsetX;
		    structures.get(47).y = bloqueTam * 6 + offsetY;
		    structures.get(47).image = cartel;
		    structures.get(47).tipo = 1;
		    
		    structures.get(48).x = bloqueTam * 10 + offsetX;
		    structures.get(48).y = bloqueTam * 6 + offsetY;
		    structures.get(48).image = cartel;
		    structures.get(48).tipo = 1;
		    
		    structures.get(49).x = bloqueTam * 11 + offsetX;
		    structures.get(49).y = bloqueTam * 5 + offsetY;
		    structures.get(49).image = cartel;
		    structures.get(49).tipo = 1;
		    
		    structures.get(50).x = bloqueTam * 11 + offsetX;
		    structures.get(50).y = bloqueTam * 3 + offsetY;
		    structures.get(50).image = cartel;
		    structures.get(50).tipo = 1;

		    structures.get(51).x = bloqueTam * 12 + offsetX;
		    structures.get(51).y = bloqueTam * 3 + offsetY;
		    structures.get(51).image = cartel;
		    structures.get(51).tipo = 1;
		    
		    structures.get(52).x = bloqueTam * 11 + offsetX;
		    structures.get(52).y = bloqueTam * 2 + offsetY;
		    structures.get(52).image = cartel;
		    structures.get(52).tipo = 1;

		    structures.get(53).x = bloqueTam * 13 + offsetX;
		    structures.get(53).y = bloqueTam * 1 + offsetY;
		    structures.get(53).image = cartel;
		    structures.get(53).tipo = 1;
		    
		    structures.get(54).x = -10000 ;
		    structures.get(54).y = -10000;
		    structures.get(54).image = cartel;
		    structures.get(54).tipo = 1;
		    
		    structures.get(55).x = -10000;
		    structures.get(55).y = -10000;
		    structures.get(55).image = cartel;
		    structures.get(55).tipo = 1;
		    
		    structures.get(56).x = -10000;
		    structures.get(56).y = -10000;
		    structures.get(56).image = cartel;
		    structures.get(56).tipo = 1;
		    
		    
		    
		    
		    
		}
		    else if(Window.frameSeleccionado == 3) { 
		    	
		    	if(createObjects == 0) {
					
					policias.add(new Police(bloqueTam * 3 + offsetX, bloqueTam * 3 + offsetY , bloqueTam, bloqueTam));
					policias.add(new Police(bloqueTam * 11 + offsetX, bloqueTam * 3 + offsetY , bloqueTam, bloqueTam));
					policias.add(new Police(bloqueTam * 3 + offsetX, bloqueTam * 7 + offsetY , bloqueTam, bloqueTam));
					policias.add(new Police(bloqueTam * 11 + offsetX, bloqueTam * 7 + offsetY , bloqueTam, bloqueTam));
					policias.add(new Police(bloqueTam * 5 + offsetX, bloqueTam * 5 + offsetY , bloqueTam, bloqueTam));
					policias.add(new Police(bloqueTam * 9 + offsetX, bloqueTam * 5 + offsetY , bloqueTam, bloqueTam));

			    	 militares.add(new Military(bloqueTam * 13 + offsetX, bloqueTam * 8+ offsetY , bloqueTam, bloqueTam));

			    	 items.add(new Item(bloqueTam * 2 + offsetX, bloqueTam * 1+ offsetY , bloqueTam, bloqueTam, player,2));
			    	 items.add(new Item(bloqueTam * 3 + offsetX, bloqueTam * 1+ offsetY , bloqueTam, bloqueTam, player,2));
			    	 items.add(new Item(bloqueTam * 4 + offsetX, bloqueTam * 1+ offsetY , bloqueTam, bloqueTam, player,2));
			    	 
			    	 items.add(new Item(bloqueTam * 4 + offsetX, bloqueTam * 1+ offsetY , bloqueTam, bloqueTam, player,2));
			    	 items.add(new Item(bloqueTam * 7 + offsetX, bloqueTam * 5+ offsetY , bloqueTam, bloqueTam, player,1));

			    	 
			    	 items.add(new Item(bloqueTam * 12 + offsetX, bloqueTam * 1+ offsetY , bloqueTam, bloqueTam, player,2));
			    	 items.add(new Item(bloqueTam * 11 + offsetX, bloqueTam * 1+ offsetY , bloqueTam, bloqueTam, player,2));
			    	 items.add(new Item(bloqueTam * 10 + offsetX, bloqueTam * 1+ offsetY , bloqueTam, bloqueTam, player,2));

			    	 items.add(new Item(bloqueTam * 3 + offsetX, bloqueTam * 8+ offsetY , bloqueTam, bloqueTam, player,2));
			    	 items.add(new Item(bloqueTam * 5 + offsetX, bloqueTam * 8+ offsetY , bloqueTam, bloqueTam, player,2));
			    	 items.add(new Item(bloqueTam * 7 + offsetX, bloqueTam * 8+ offsetY , bloqueTam, bloqueTam, player,2));
			    	 items.add(new Item(bloqueTam * 9 + offsetX, bloqueTam * 8+ offsetY , bloqueTam, bloqueTam, player,2));
			    	 items.add(new Item(bloqueTam * 11 + offsetX, bloqueTam * 8+ offsetY , bloqueTam, bloqueTam, player,2));

			    	 
			    	 
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
			    structures.get(0).image = banca;
			    
			    structures.get(1).x = bloqueTam * 2 + offsetX;
			    structures.get(1).y = bloqueTam * 4+ offsetY;
			    structures.get(1).image = banca;
			    
			    structures.get(2).x = bloqueTam * 2 + offsetX;
			    structures.get(2).y = bloqueTam * 6+ offsetY;
			    structures.get(2).image = banca;

			    structures.get(3).x = bloqueTam * 2 + offsetX;
			    structures.get(3).y = bloqueTam * 8+ offsetY;
			    structures.get(3).image = banca;
			    
			    structures.get(4).x = bloqueTam * 2 + offsetX;
			    structures.get(4).y = bloqueTam * 10+ offsetY;
			    structures.get(4).image = banca;

			    structures.get(5).x = bloqueTam * 4 + offsetX;
			    structures.get(5).y = bloqueTam * 2+ offsetY;
			    structures.get(5).image = banca;
			    
			    structures.get(6).x = bloqueTam * 6 + offsetX;
			    structures.get(6).y = bloqueTam * 2+ offsetY;
			    structures.get(6).image = banca;
		    
			    structures.get(7).x = bloqueTam * 8 + offsetX;
			    structures.get(7).y = bloqueTam * 2+ offsetY;
			    structures.get(7).image = banca;
			    
			    structures.get(8).x = bloqueTam * 10 + offsetX;
			    structures.get(8).y = bloqueTam * 2+ offsetY;
			    structures.get(8).image = banca;
			    
			    structures.get(9).x = bloqueTam * 12 + offsetX;
			    structures.get(9).y = bloqueTam * 2+ offsetY;
			    structures.get(9).image = banca;
			    
			    structures.get(10).x = bloqueTam * 4 + offsetX;
			    structures.get(10).y = bloqueTam * 4+ offsetY;
			    structures.get(10).image = banca;
			    
			    structures.get(11).x = bloqueTam * 6 + offsetX;
			    structures.get(11).y = bloqueTam * 4+ offsetY;
			    structures.get(11).image = banca;
			    
			    structures.get(12).x = bloqueTam * 8 + offsetX;
			    structures.get(12).y = bloqueTam * 4+ offsetY;
			    structures.get(12).image = banca;
			    
			    structures.get(13).x = bloqueTam * 10 + offsetX;
			    structures.get(13).y = bloqueTam * 4+ offsetY;
			    structures.get(13).image = banca;
			    
			    structures.get(14).x = bloqueTam * 12 + offsetX;
			    structures.get(14).y = bloqueTam * 4+ offsetY;
			    structures.get(14).image = banca;
			    
			    structures.get(15).x = bloqueTam * 4 + offsetX;
			    structures.get(15).y = bloqueTam * 6+ offsetY;
			    structures.get(15).image = banca;
			    
			    structures.get(16).x = bloqueTam * 6 + offsetX;
			    structures.get(16).y = bloqueTam * 6+ offsetY;
			    structures.get(16).image = banca;
			    
			    structures.get(17).x = bloqueTam * 8 + offsetX;
			    structures.get(17).y = bloqueTam * 6+ offsetY;
			    structures.get(17).image = banca;
			    
			    structures.get(18).x = bloqueTam * 10 + offsetX;
			    structures.get(18).y = bloqueTam * 6+ offsetY;
			    structures.get(18).image = banca;
			    
			    structures.get(19).x = bloqueTam * 12 + offsetX;
			    structures.get(19).y = bloqueTam * 6+ offsetY;
			    structures.get(19).image = banca;
			    
			    structures.get(20).x = bloqueTam * 4 + offsetX;
			    structures.get(20).y = bloqueTam * 8+ offsetY;
			    structures.get(20).image = banca;
			    
			    structures.get(21).x = bloqueTam * 6 + offsetX;
			    structures.get(21).y = bloqueTam * 8+ offsetY;
			    structures.get(21).image = banca;
			    
			    structures.get(22).x = bloqueTam * 8 + offsetX;
			    structures.get(22).y = bloqueTam * 8+ offsetY;
			    structures.get(22).image = banca;
			    
			    structures.get(23).x = bloqueTam * 10 + offsetX;
			    structures.get(23).y = bloqueTam * 8+ offsetY;
			    structures.get(23).image = banca;

			    structures.get(24).x = bloqueTam * 12 + offsetX;
			    structures.get(24).y = bloqueTam * 8+ offsetY;
			    structures.get(24).image = banca;
			    
			    structures.get(25).x = -10000;
			    structures.get(25).y = -10000;
			    structures.get(25).image = banca;
			    
			    structures.get(26).x = -10000;
			    structures.get(26).y = -10000;
			    structures.get(26).image = banca;
			    
			    structures.get(27).x = -10000;
			    structures.get(27).y = -10000;
			    structures.get(27).image = banca;
			    
			    structures.get(28).x = -10000;
			    structures.get(28).y = -10000;
			    structures.get(28).image = banca;
			    
			    structures.get(29).x = -10000;
			    structures.get(29).y = -10000;
			    structures.get(29).image = banca;
			    
			    structures.get(30).x = -10000;
			    structures.get(30).y = -10000;
			    structures.get(30).image = banca;
			    
			    
			    structures.get(33).x = -10000;
			    structures.get(33).y = -10000;
			    structures.get(33).image = banca;
			    
			    structures.get(36).x = -10000;
			    structures.get(36).y = -10000;
			    structures.get(36).image = banca;
			    
			    structures.get(37).x = -10000;
			    structures.get(37).y = -10000;
			    structures.get(37).image = banca;
			    
			    structures.get(40).x = -10000;
			    structures.get(40).y = -10000;
			    structures.get(40).image = banca;
			    
			    structures.get(47).x = -10000;
			    structures.get(47).y = -10000;
			    structures.get(47).image = banca;
			    
			    structures.get(48).x = -10000;
			    structures.get(48).y = -10000;
			    structures.get(48).image = banca;
			    
			    structures.get(49).x = -10000;
			    structures.get(49).y = -10000;
			    structures.get(49).image = banca;
			    
			    structures.get(50).x = -10000;
			    structures.get(50).y = -10000;
			    structures.get(50).image = banca;
			    
			    structures.get(51).x = -10000;
			    structures.get(51).y = -10000;
			    structures.get(51).image = banca;
			    
			    structures.get(52).x = -10000;
			    structures.get(52).y = -10000;
			    structures.get(52).image = banca;
			    
			    
			    structures.get(53).x = -10000;
			    structures.get(53).y = -10000;
			    structures.get(53).image = banca;
			    
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
		            
		            structures1.x = -10000;
		            structures1.y = -10000;
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
		            	g.drawImage(N2F1ESQ_IZQ_SUP, x, y, bloqueTam, bloqueTam, null);
            		}
            		
            		// Arriba a la derecha
            		else if(j == 0 && i == numColumnas-1) {
		            	g.drawImage(N2F1ESQ_DER_SUP, x, y, bloqueTam, bloqueTam, null);
            		}
            		
            		// Abajo a la derecha
            		else if(j == numFilas-1 && i == numColumnas-1) {
		            	g.drawImage(N2F1ESQ_DER_INF, x, y, bloqueTam, bloqueTam, null);
            		}
            		
            		// Abajo a la izquierda
            		else if(j == numFilas-1 && i == 0) {
		            	g.drawImage(N2F1ESQ_IZQ_INF, x, y, bloqueTam, bloqueTam, null);
            		}
            		
            		// Bordes de arriba 
            		else if(j == 0) {
		            	g.drawImage(N2F1BORDE_ARRIBA, x, y, bloqueTam, bloqueTam, null);		            		
	            	}
	            	
            		// Bordes de abajo
	            	else if(j == numFilas - 1) {
		            	g.drawImage(N2F1BORDE_ABAJO, x, y, bloqueTam, bloqueTam, null);	
	            	}
	            	
            		// Bordes de la izquierda
	            	else if(i == 0) {
	            	    g.drawImage(N2F1BORDE_IZQ, x, y, bloqueTam, bloqueTam, null);
	    
	            	}
	            	
            		// Bordes de la derecha
	            	else if(i == numColumnas - 1) {
	            	    g.drawImage(N2F1BORDE_DER, x, y, bloqueTam, bloqueTam, null);
	            	}

	            		
            		// Suelo
	            	else {
	            		g.drawImage(N2F1SUELO, x, y, bloqueTam, bloqueTam, null);
	            	}
            	}
	            
	            	
	            	if(frameSeleccionado == 2) {
	            		// Arriba a la izquierda
	            		if(j == 0 && i == 0) {
			            	g.drawImage(N2F2ESQ_IZQ_SUP, x, y, bloqueTam, bloqueTam, null);
	            		}
	            		
	            		// Arriba a la derecha
	            		else if(j == 0 && i == numColumnas-1) {
			            	g.drawImage(N2F2ESQ_DER_SUP, x, y, bloqueTam, bloqueTam, null);
	            		}
	            		
	            		// Abajo a la derecha
	            		else if(j == numFilas-1 && i == numColumnas-1) {
			            	g.drawImage(N2F2ESQ_DER_INF, x, y, bloqueTam, bloqueTam, null);
	            		}
	            		
	            		// Abajo a la izquierda
	            		else if(j == numFilas-1 && i == 0) {
			            	g.drawImage(N2F2ESQ_IZQ_INF, x, y, bloqueTam, bloqueTam, null);
	            		}
	            		
	            		// Bordes de arriba 
	            		else if(j == 0) {
			            	g.drawImage(N2F2BORDE_ARRIBA, x, y, bloqueTam, bloqueTam, null);		            		
		            	}
		            	
	            		// Bordes de abajo
		            	else if(j == numFilas - 1) {
		            	    g.drawImage(N2F2BORDE_ABAJO, x, y, bloqueTam, bloqueTam, null);
		            	}
		            	
	            		// Bordes de la izquierda
		            	else if(i == 0) {
		            	    g.drawImage(N2F2BORDE_IZQ, x, y, bloqueTam, bloqueTam, null);
		            	}
		            	
	            		// Bordes de la derecha
		            	else if(i == numColumnas - 1) {  
		            	    g.drawImage(N2F2BORDE_DER, x, y, bloqueTam, bloqueTam, null);
		            	}

		            		
	            		// Suelo
		            	else {
		            		g.drawImage(N2F2SUELO, x, y, bloqueTam, bloqueTam, null);
		            	} 
	            	}
	            	
	            	
	            	if(frameSeleccionado == 3) {
	            		
	            		// Arriba a la izquierda
	            		if(j == 0 && i == 0) {
			            	g.drawImage(N2F3ESQ_IZQ_SUP, x, y, bloqueTam, bloqueTam, null);
	            		}
	            		
	            		// Arriba a la derecha
	            		else if(j == 0 && i == numColumnas-1) {
			            	g.drawImage(N2F3ESQ_DER_SUP, x, y, bloqueTam, bloqueTam, null);
	            		}
	            		
	            		// Abajo a la derecha
	            		else if(j == numFilas-1 && i == numColumnas-1) {
			            	g.drawImage(N2F3ESQ_DER_INF, x, y, bloqueTam, bloqueTam, null);
	            		}
	            		
	            		// Abajo a la izquierda
	            		else if(j == numFilas-1 && i == 0) {
			            	g.drawImage(N2F3ESQ_IZQ_INF, x, y, bloqueTam, bloqueTam, null);
	            		}
	            		
	            		// Bordes de arriba 
	            		else if(j == 0) {
			            	g.drawImage(N2F3BORDE_ARRIBA, x, y, bloqueTam, bloqueTam, null);		            		
		            	}
		            	
	            		// Bordes de abajo
		            	else if(j == numFilas - 1) {
			            	g.drawImage(N2F3BORDE_ABAJO, x, y, bloqueTam, bloqueTam, null);	
		            	}
		            	
	            		// Bordes de la izquierda
		            	else if(i == 0) {
			            	g.drawImage(N2F3BORDE_IZQ, x, y, bloqueTam, bloqueTam, null);	
		            	}
		            	
	            		// Bordes de la derecha
		            	else if(i == numColumnas - 1) {
			            	g.drawImage(N2F3BORDE_DER, x, y, bloqueTam, bloqueTam, null);	

		            	}
		            		
	            		// Suelo
		            	else {
		            		g.drawImage(N2F3SUELO, x, y, bloqueTam, bloqueTam, null);
		            	} 
	            		
	            	}
	        }
	    }
	}
}

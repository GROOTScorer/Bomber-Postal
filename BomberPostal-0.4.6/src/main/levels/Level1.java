package main.levels;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

import main.entities.Power_Up;
import main.entities.characters.Player;
import main.entities.environment.Structure;
import main.ui.Window;

public class Level1 {

	
	// ----------------------- Declaracion de variables
	
	private int invalido = 0;
	
    private Image barraLateralImage;
    long lastPressedTime = 0;
    boolean canPress = true;
    private Image 
    
    N1F1PA1,N1F1PA2,N1F1PA3,N1F1PA4,N1F1PA5,N1F1PA6,N1F1PA7,N1F1PA8,N1F1PA9,N1F1INTERIOR, N1F1SUELO, N1F1BORDE_ABAJO,N1F1BORDE_ARRIBA,N1F1BORDE_DER,N1F1BORDE_IZQ, N1F1ESQ_IZQ_INF, 
    N1F1ESQ_IZQ_SUP, N1F1ESQ_DER_INF, N1F1ESQ_DER_SUP, N1F1PD, N1F1PL, N1F1PR ,N1F1PU, impresora, planta, trapeador,
    
    N1F2PA1,N1F2PA2,N1F2PA3,N1F2PA4,N1F2PA5,N1F2PA6,N1F2PA7,N1F2PA8,N1F2PA9,N1F2INTERIOR, N1F2SUELO, N1F2BORDE_ABAJO,N1F2BORDE_ARRIBA,N1F2BORDE_DER,N1F2BORDE_IZQ, N1F2ESQ_IZQ_INF, 
    N1F2ESQ_IZQ_SUP, N1F2ESQ_DER_INF, N1F2ESQ_DER_SUP, N1F2PD, N1F2PL, N1F2PR ,N1F2PU, latas,
    
    N1F3PA, N1F3SUELO, N1F3BORDE_ABAJO,N1F3BORDE_ARRIBA,N1F3BORDE_DER,N1F3BORDE_IZQ, N1F3ESQ_IZQ_INF, N1F3ALFOMBRA ,
    N1F3ESQ_IZQ_SUP, N1F3ESQ_DER_INF, N1F3ESQ_DER_SUP, N1F3PD, N1F3PL, N1F3PR ,N1F3PU, cajero, marmol, N1F3BORDE_DERECHA;  

	// ----------------------- Fin de Declaracion de variables
	
	// Constructor
	public Level1() {
        try {
            barraLateralImage = ImageIO.read(new File("assets/images/lateralImage.jpg"));
            N1F1PA1 = ImageIO.read(new File("assets/images/SpritesBomberPostal/Mapa/Dia1/1_Oficina/ParedOficinaColumna.png"));
            N1F1PA2 = ImageIO.read(new File("assets/images/SpritesBomberPostal/Mapa/Dia1/1_Oficina/ParedOficinaExtremoDerecho.png"));
            N1F1PA3 = ImageIO.read(new File("assets/images/SpritesBomberPostal/Mapa/Dia1/1_Oficina/ParedOficinaExtremoInferior.png"));
            N1F1PA4 = ImageIO.read(new File("assets/images/SpritesBomberPostal/Mapa/Dia1/1_Oficina/ParedOficinaExtremoIzquierdo.png"));
            N1F1PA5 = ImageIO.read(new File("assets/images/SpritesBomberPostal/Mapa/Dia1/1_Oficina/ParedOficinaExtremoSuperior.png"));
            N1F1PA6 = ImageIO.read(new File("assets/images/SpritesBomberPostal/Mapa/Dia1/1_Oficina/ParedOficinaInferiorDerecho.png"));
            N1F1PA7 = ImageIO.read(new File("assets/images/SpritesBomberPostal/Mapa/Dia1/1_Oficina/ParedOficinaInferiorIzquierdo.png"));
            N1F1PA8 = ImageIO.read(new File("assets/images/SpritesBomberPostal/Mapa/Dia1/1_Oficina/ParedOficinaSuperiorDerecho.png"));
            N1F1PA9 = ImageIO.read(new File("assets/images/SpritesBomberPostal/Mapa/Dia1/1_Oficina/ParedOficinaSuperiorIzquierdo.png"));
            N1F1PD = ImageIO.read(new File("assets/images/SpritesBomberPostal/Mapa/Dia1/1_Oficina/ParedOficinaConexionDown.png"));
            N1F1PL = ImageIO.read(new File("assets/images/SpritesBomberPostal/Mapa/Dia1/1_Oficina/ParedOficinaConexionLeft.png"));
            N1F1PR = ImageIO.read(new File("assets/images/SpritesBomberPostal/Mapa/Dia1/1_Oficina/ParedOficinaConexionRight.png"));
            N1F1PU = ImageIO.read(new File("assets/images/SpritesBomberPostal/Mapa/Dia1/1_Oficina/ParedOficinaConexionUp.png"));
            N1F1SUELO = ImageIO.read(new File("assets/images/SpritesBomberPostal/Mapa/Dia1/1_Oficina/BaldosaOficina.png"));
            N1F1BORDE_ARRIBA= ImageIO.read(new File("assets/images/SpritesBomberPostal/Mapa/Dia1/1_Oficina/BloqueBordeOficina.png"));
            N1F1BORDE_ABAJO= ImageIO.read(new File("assets/images/SpritesBomberPostal/Mapa/Dia1/1_Oficina/BloqueBordeOficinaInferior.png"));
            N1F1BORDE_IZQ= ImageIO.read(new File("assets/images/SpritesBomberPostal/Mapa/Dia1/1_Oficina/BloqueBordeOficinaIzquierda.png"));
            N1F1BORDE_DER= ImageIO.read(new File("assets/images/SpritesBomberPostal/Mapa/Dia1/1_Oficina/BloqueBordeOficinaDerecha.png"));
            N1F1INTERIOR = ImageIO.read(new File("assets/images/SpritesBomberPostal/Mapa/Dia1/1_Oficina/InteriorPared.png"));
            N1F1ESQ_IZQ_INF = ImageIO.read(new File("assets/images/SpritesBomberPostal/Mapa/Dia1/1_Oficina/BordeOficinaEsquinaInferiorIzquierda.png"));
            N1F1ESQ_IZQ_SUP = ImageIO.read(new File("assets/images/SpritesBomberPostal/Mapa/Dia1/1_Oficina/BordeOficinaEsquinaSuperiorIzquierda.png"));
            N1F1ESQ_DER_INF = ImageIO.read(new File("assets/images/SpritesBomberPostal/Mapa/Dia1/1_Oficina/BordeOficinaEsquinaInferiorDerecha.png"));
            N1F1ESQ_DER_SUP = ImageIO.read(new File("assets/images/SpritesBomberPostal/Mapa/Dia1/1_Oficina/BordeOficinaEsquinaSuperiorDerecha.png"));
            impresora = ImageIO.read(new File("assets/images/SpritesBomberPostal/Mapa/Dia1/1_Oficina/Impresora.png"));
            planta = ImageIO.read(new File("assets/images/SpritesBomberPostal/Mapa/Dia1/1_Oficina/Planta.png"));
            trapeador = ImageIO.read(new File("assets/images/SpritesBomberPostal/Mapa/Dia1/1_Oficina/Trapeador.png"));
            
            N1F2PA1 = ImageIO.read(new File("assets/images/SpritesBomberPostal/Mapa/Dia1/2_Supermercado/ParedSuperColumna.png"));
            N1F2PA2 = ImageIO.read(new File("assets/images/SpritesBomberPostal/Mapa/Dia1/2_Supermercado/ParedSuperExtremoDerecho.png"));
            N1F2PA3 = ImageIO.read(new File("assets/images/SpritesBomberPostal/Mapa/Dia1/2_Supermercado/ParedSuperExtremoInferior.png"));
            N1F2PA4 = ImageIO.read(new File("assets/images/SpritesBomberPostal/Mapa/Dia1/2_Supermercado/ParedSuperExtremoIziquierdo.png"));
            N1F2PA5 = ImageIO.read(new File("assets/images/SpritesBomberPostal/Mapa/Dia1/2_Supermercado/ParedSuperExtremoSuperior.png"));
            N1F2PA6 = ImageIO.read(new File("assets/images/SpritesBomberPostal/Mapa/Dia1/2_Supermercado/ParedSuperInferiorDerecho.png"));
            N1F2PA7 = ImageIO.read(new File("assets/images/SpritesBomberPostal/Mapa/Dia1/2_Supermercado/ParedSuperInferiorIzquierdo.png"));
            N1F2PA8 = ImageIO.read(new File("assets/images/SpritesBomberPostal/Mapa/Dia1/2_Supermercado/ParedSuperSuperiorDerecho.png"));
            N1F2PA9 = ImageIO.read(new File("assets/images/SpritesBomberPostal/Mapa/Dia1/2_Supermercado/ParedSuperSuperiorIzquierdo.png"));
            N1F2SUELO = ImageIO.read(new File("assets/images/SpritesBomberPostal/Mapa/Dia1/2_Supermercado/BaldosaSupermercado.png"));
            
            N1F2BORDE_ARRIBA= ImageIO.read(new File("assets/images/SpritesBomberPostal/Mapa/Dia1/2_Supermercado/BloqueBordeSupermercado.png"));
            N1F2BORDE_ABAJO= ImageIO.read(new File("assets/images/SpritesBomberPostal/Mapa/Dia1/2_Supermercado/BloqueBordeSupermercadoInferior.png"));
            N1F2BORDE_IZQ= ImageIO.read(new File("assets/images/SpritesBomberPostal/Mapa/Dia1/2_Supermercado/BloqueBordeSupermercadoIzquierdo.png"));
            N1F2BORDE_DER= ImageIO.read(new File("assets/images/SpritesBomberPostal/Mapa/Dia1/2_Supermercado/BloqueBordeSupermercadoDerecha.png"));
            
            N1F2ESQ_IZQ_INF = ImageIO.read(new File("assets/images/SpritesBomberPostal/Mapa/Dia1/2_Supermercado/BordeSuperEsquinaInferiorIzquierda.png"));
            N1F2ESQ_DER_INF = ImageIO.read(new File("assets/images/SpritesBomberPostal/Mapa/Dia1/2_Supermercado/BordeSuperEsquinaInferiorDerecha.png"));
            N1F2ESQ_DER_SUP = ImageIO.read(new File("assets/images/SpritesBomberPostal/Mapa/Dia1/2_Supermercado/BordeSuperEsquinaSuperiorDerecha.png")); 
            N1F2ESQ_IZQ_SUP = ImageIO.read(new File("assets/images/SpritesBomberPostal/Mapa/Dia1/2_Supermercado/BordeSuperEsquinaSuperiorIzquierda.png"));
            latas = ImageIO.read(new File("assets/images/SpritesBomberPostal/Mapa/Dia1/2_Supermercado/latas.png"));
            
            N1F3ALFOMBRA = ImageIO.read(new File("assets/images/SpritesBomberPostal/Mapa/Dia1/3_Banco/AlfombraBanco.png"));
            N1F3PA = ImageIO.read(new File("assets/images/SpritesBomberPostal/Mapa/Dia1/3_Banco/ParedBanco.png"));
            N1F3SUELO = ImageIO.read(new File("assets/images/SpritesBomberPostal/Mapa/Dia1/3_Banco/PisoBanco.png"));
            N1F3ESQ_IZQ_INF = ImageIO.read(new File("assets/images/SpritesBomberPostal/Mapa/Dia1/3_Banco/BordeBancoEsquinaInferiorIzquierda.png"));
            N1F3ESQ_DER_INF = ImageIO.read(new File("assets/images/SpritesBomberPostal/Mapa/Dia1/3_Banco/BordeBancoEsquinaInferiorDerecha.png"));
            N1F3ESQ_DER_SUP = ImageIO.read(new File("assets/images/SpritesBomberPostal/Mapa/Dia1/3_Banco/BordeBancoEsquinaSuperiorDerecha.png")); 
            N1F3ESQ_IZQ_SUP = ImageIO.read(new File("assets/images/SpritesBomberPostal/Mapa/Dia1/3_Banco/BordeBancoEsquinaSuperiorIzquierda.png"));
            N1F3BORDE_DERECHA= ImageIO.read(new File("assets/images/SpritesBomberPostal/Mapa/Dia1/3_Banco/BloqueBordeBancoDerecha.jpg"));
            
            N1F3BORDE_ARRIBA= ImageIO.read(new File("assets/images/SpritesBomberPostal/Mapa/Dia1/3_Banco/BloqueBordeBanco.png"));
            N1F3BORDE_ABAJO= ImageIO.read(new File("assets/images/SpritesBomberPostal/Mapa/Dia1/3_Banco/BloqueBordeBancoInferior.png"));
            N1F3BORDE_IZQ= ImageIO.read(new File("assets/images/SpritesBomberPostal/Mapa/Dia1/3_Banco/BloqueBordeBancoIzquierda.png"));
            N1F3BORDE_DER= ImageIO.read(new File("assets/images/SpritesBomberPostal/Mapa/Dia1/3_Banco/BloqueBordeBancoDerecha.png"));
            
            cajero = ImageIO.read(new File("assets/images/SpritesBomberPostal/Mapa/Dia1/3_Banco/Cajero.png"));
            marmol = ImageIO.read(new File("assets/images/SpritesBomberPostal/Mapa/Dia1/3_Banco/ColumnaBanco.png"));
            
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public void update(List<Structure> structures, int bloqueTam, int offsetX, int offsetY, boolean iPressed, Player player, List<Power_Up> powerups) {			
		
		if(invalido != 1) {
	    	 // PRIMERA OFICINA. 
	    	 structures.add(new Structure(bloqueTam * 2 + offsetX, bloqueTam * 2 + offsetY , bloqueTam, bloqueTam, N1F1PA3,0,powerups));
	    	 structures.add(new Structure(bloqueTam * 3 + offsetX, bloqueTam * 2 + offsetY , bloqueTam, bloqueTam, N1F1PA3,0,powerups));
	    	 structures.add(new Structure(bloqueTam * 4 + offsetX, bloqueTam * 2 + offsetY , bloqueTam, bloqueTam, N1F1PA6,0,powerups));
	    	 structures.add(new Structure(bloqueTam * 2 + offsetX, bloqueTam * 3 + offsetY , bloqueTam, bloqueTam, N1F1PA3,0,powerups));
	    	 structures.add(new Structure(bloqueTam * 2 + offsetX, bloqueTam * 4 + offsetY , bloqueTam, bloqueTam, N1F1PA3,0,powerups));
	    	 structures.add(new Structure(bloqueTam * 3 + offsetX, bloqueTam * 4 + offsetY , bloqueTam, bloqueTam, N1F1PA3,0,powerups));
	    	 structures.add(new Structure(bloqueTam * 4 + offsetX, bloqueTam * 4 + offsetY , bloqueTam, bloqueTam, N1F1PA3,0,powerups));
	    	 structures.add(new Structure(bloqueTam * 4 + offsetX, bloqueTam * 3 + offsetY , bloqueTam, bloqueTam, N1F1PA3,0,powerups));
	    	 structures.add(new Structure(bloqueTam * 3 + offsetX, bloqueTam * 3 + offsetY , bloqueTam, bloqueTam, N1F1PA3,0,powerups));
	    	 // 9
	    	 
	    	 // SEGUNDA OFICINA.
	    	 structures.add(new Structure(bloqueTam * 7 + offsetX, bloqueTam * 2 + offsetY , bloqueTam, bloqueTam, N1F1PA3,0,powerups));
	    	 structures.add(new Structure(bloqueTam * 8 + offsetX, bloqueTam * 2 + offsetY , bloqueTam, bloqueTam, N1F1PA3,0,powerups));
	    	 structures.add(new Structure(bloqueTam * 9 + offsetX, bloqueTam * 2 + offsetY , bloqueTam, bloqueTam, N1F1PA6,0,powerups));
	    	 structures.add(new Structure(bloqueTam * 7 + offsetX, bloqueTam * 3 + offsetY , bloqueTam, bloqueTam, N1F1PA3,0,powerups));
	    	 structures.add(new Structure(bloqueTam * 7 + offsetX, bloqueTam * 4 + offsetY , bloqueTam, bloqueTam, N1F1PA3,0,powerups));
	    	 structures.add(new Structure(bloqueTam * 8 + offsetX, bloqueTam * 4 + offsetY , bloqueTam, bloqueTam, N1F1PA3,0,powerups));
	    	 structures.add(new Structure(bloqueTam * 9 + offsetX, bloqueTam * 4 + offsetY , bloqueTam, bloqueTam, N1F1PA3,0,powerups));
	    	 structures.add(new Structure(bloqueTam * 9 + offsetX, bloqueTam * 3 + offsetY , bloqueTam, bloqueTam, N1F1PA3,0,powerups));
	    	 structures.add(new Structure(bloqueTam * 8 + offsetX, bloqueTam * 3 + offsetY , bloqueTam, bloqueTam, N1F1PA3,0,powerups));
	    	 // 18
	    	 
	    	 // TERCERA OFICINA.
	    	 structures.add(new Structure(bloqueTam * 2 + offsetX, bloqueTam * 6 + offsetY , bloqueTam, bloqueTam, N1F1PA3,0,powerups));
	    	 structures.add(new Structure(bloqueTam * 3 + offsetX, bloqueTam * 6 + offsetY , bloqueTam, bloqueTam, N1F1PA3,0,powerups));
	    	 structures.add(new Structure(bloqueTam * 4 + offsetX, bloqueTam * 6 + offsetY , bloqueTam, bloqueTam, N1F1PA6,0,powerups));
	    	 structures.add(new Structure(bloqueTam * 2 + offsetX, bloqueTam * 7 + offsetY , bloqueTam, bloqueTam, N1F1PA3,0,powerups));
	    	 structures.add(new Structure(bloqueTam * 2 + offsetX, bloqueTam * 8 + offsetY , bloqueTam, bloqueTam, N1F1PA3,0,powerups));
	    	 structures.add(new Structure(bloqueTam * 3 + offsetX, bloqueTam * 8 + offsetY , bloqueTam, bloqueTam, N1F1PA3,0,powerups));
	    	 structures.add(new Structure(bloqueTam * 4 + offsetX, bloqueTam * 8 + offsetY , bloqueTam, bloqueTam, N1F1PA3,0,powerups));
	    	 structures.add(new Structure(bloqueTam * 4 + offsetX, bloqueTam * 7 + offsetY , bloqueTam, bloqueTam, N1F1PA3,0,powerups));
	    	 structures.add(new Structure(bloqueTam * 3 + offsetX, bloqueTam * 7 + offsetY , bloqueTam, bloqueTam, N1F1PA3,0,powerups));
	    	 // 27
	    	 
	    	 // CUARTA OFICINA.
	    	 structures.add(new Structure(bloqueTam * 7 + offsetX, bloqueTam * 6 + offsetY , bloqueTam, bloqueTam, N1F1PA3,0,powerups));
	    	 structures.add(new Structure(bloqueTam * 8 + offsetX, bloqueTam * 6 + offsetY , bloqueTam, bloqueTam, N1F1PA3,0,powerups));
	    	 structures.add(new Structure(bloqueTam * 9 + offsetX, bloqueTam * 6 + offsetY , bloqueTam, bloqueTam, N1F1PA6,0,powerups));
	    	 structures.add(new Structure(bloqueTam * 7 + offsetX, bloqueTam * 7 + offsetY , bloqueTam, bloqueTam, N1F1PA3,0,powerups));
	    	 structures.add(new Structure(bloqueTam * 7 + offsetX, bloqueTam * 8 + offsetY , bloqueTam, bloqueTam, N1F1PA3,0,powerups));
	    	 structures.add(new Structure(bloqueTam * 8 + offsetX, bloqueTam * 8 + offsetY , bloqueTam, bloqueTam, N1F1PA3,0,powerups));
	    	 structures.add(new Structure(bloqueTam * 9 + offsetX, bloqueTam * 8 + offsetY , bloqueTam, bloqueTam, N1F1PA3,0,powerups));
	    	 structures.add(new Structure(bloqueTam * 9 + offsetX, bloqueTam * 7 + offsetY , bloqueTam, bloqueTam, N1F1PA3,0,powerups));
	    	 structures.add(new Structure(bloqueTam * 8 + offsetX, bloqueTam * 7 + offsetY , bloqueTam, bloqueTam, N1F1PA3,0,powerups));
	    	 // 36
	    	 
	    	 structures.add(new Structure(bloqueTam * 1 + offsetX, bloqueTam * 3 + offsetY , bloqueTam, bloqueTam, N1F1PA3,1,powerups)); // Impresora
	    	 structures.add(new Structure(bloqueTam * 1 + offsetX, bloqueTam * 7 + offsetY , bloqueTam, bloqueTam, N1F1PA3,1,powerups)); // Impresora
	    	 structures.add(new Structure(bloqueTam * 6 + offsetX, bloqueTam * 1 + offsetY , bloqueTam, bloqueTam, N1F1PA3,1,powerups)); // Plantas	
	    	 structures.add(new Structure(bloqueTam * 13 + offsetX, bloqueTam * 8 + offsetY , bloqueTam, bloqueTam, N1F1PA3,1,powerups)); // Trapeador
	    	 
	    	 // OFICINAS LATERALES
	    	 structures.add(new Structure(bloqueTam * 11 + offsetX, bloqueTam * 1 + offsetY , bloqueTam, bloqueTam, N1F1PA3,0,powerups));
	    	 structures.add(new Structure(bloqueTam * 12 + offsetX, bloqueTam * 1 + offsetY , bloqueTam, bloqueTam, N1F1PA3,0,powerups));
	    	 structures.add(new Structure(bloqueTam * 13 + offsetX, bloqueTam * 1 + offsetY , bloqueTam, bloqueTam, N1F1PA3,0,powerups));
	    	 structures.add(new Structure(bloqueTam * 11 + offsetX, bloqueTam * 3 + offsetY , bloqueTam, bloqueTam, N1F1PA3,0,powerups));
	    	 structures.add(new Structure(bloqueTam * 12 + offsetX, bloqueTam * 3 + offsetY , bloqueTam, bloqueTam, N1F1PA3,0,powerups));
	    	 structures.add(new Structure(bloqueTam * 13 + offsetX, bloqueTam * 3 + offsetY , bloqueTam, bloqueTam, N1F1PA3,0,powerups));
	    	 structures.add(new Structure(bloqueTam * 11 + offsetX, bloqueTam * 5 + offsetY , bloqueTam, bloqueTam, N1F1PA3,0,powerups));
	    	 structures.add(new Structure(bloqueTam * 12 + offsetX, bloqueTam * 5 + offsetY , bloqueTam, bloqueTam, N1F1PA3,0,powerups));
	    	 structures.add(new Structure(bloqueTam * 13 + offsetX, bloqueTam * 5 + offsetY , bloqueTam, bloqueTam, N1F1PA3,0,powerups));
	    	 structures.add(new Structure(bloqueTam * 11 + offsetX, bloqueTam * 7 + offsetY , bloqueTam, bloqueTam, N1F1PA3,0,powerups));
	    	 structures.add(new Structure(bloqueTam * 12 + offsetX, bloqueTam * 7 + offsetY , bloqueTam, bloqueTam, N1F1PA3,0,powerups));
	    	 structures.add(new Structure(bloqueTam * 13 + offsetX, bloqueTam * 7 + offsetY , bloqueTam, bloqueTam, N1F1PA3,0,powerups));
	    	 
	    	 
	    	 // FRAME 2
	    	 structures.add(new Structure(-10000, -10000 , bloqueTam, bloqueTam, N1F1PA3,0,powerups));
	    	 structures.add(new Structure(-10000, -10000 , bloqueTam, bloqueTam, N1F1PA3,0,powerups));
	    	 structures.add(new Structure(-10000, -10000 , bloqueTam, bloqueTam, N1F1PA3,0,powerups));
	    	 structures.add(new Structure(-10000, -10000 , bloqueTam, bloqueTam, N1F1PA3,0,powerups));
	    	 structures.add(new Structure(-10000, -10000 , bloqueTam, bloqueTam, N1F1PA3,0,powerups));
	    	 structures.add(new Structure(-10000, -10000 , bloqueTam, bloqueTam, N1F1PA3,0,powerups));
	    	 structures.add(new Structure(-10000, -10000 , bloqueTam, bloqueTam, N1F1PA3,0,powerups));
	    	 structures.add(new Structure(-10000, -10000 , bloqueTam, bloqueTam, N1F1PA3,0,powerups));
	    	 structures.add(new Structure(-10000, -10000 , bloqueTam, bloqueTam, N1F1PA3,0,powerups));
	    	 structures.add(new Structure(-10000, -10000 , bloqueTam, bloqueTam, N1F1PA3,0,powerups));
	    	 structures.add(new Structure(-10000, -10000 , bloqueTam, bloqueTam, N1F1PA3,0,powerups));
	    	 structures.add(new Structure(-10000, -10000 , bloqueTam, bloqueTam, N1F1PA3,0,powerups));
	    	 structures.add(new Structure(-10000, -10000 , bloqueTam, bloqueTam, N1F1PA3,0,powerups));
	    	 structures.add(new Structure(-10000, -10000 , bloqueTam, bloqueTam, N1F1PA3,0,powerups));
	    	 structures.add(new Structure(-10000, -10000 , bloqueTam, bloqueTam, N1F1PA3,0,powerups));


	    	 
	    	 // FRAME 3
	    	 structures.add(new Structure(-10000, -10000 , bloqueTam, bloqueTam, N1F2PA3,0,powerups));
	    	 structures.add(new Structure(-10000, -10000 , bloqueTam, bloqueTam, N1F2PA3,0,powerups));
	    	 structures.add(new Structure(-10000, -10000 , bloqueTam, bloqueTam, N1F2PA3,0,powerups));
	    	 structures.add(new Structure(-10000, -10000 , bloqueTam, bloqueTam, N1F2PA3,0,powerups));
	    	 structures.add(new Structure(-10000, -10000 , bloqueTam, bloqueTam, N1F2PA3,0,powerups));
	    	 structures.add(new Structure(-10000, -10000 , bloqueTam, bloqueTam, N1F2PA3,0,powerups));
	    	 structures.add(new Structure(-10000, -10000 , bloqueTam, bloqueTam, N1F2PA3,0,powerups));
	    	 structures.add(new Structure(-10000, -10000 , bloqueTam, bloqueTam, N1F2PA3,0,powerups));
	    	 structures.add(new Structure(-10000, -10000 , bloqueTam, bloqueTam, N1F2PA3,0,powerups));
	    	 structures.add(new Structure(-10000, -10000 , bloqueTam, bloqueTam, N1F2PA3,0,powerups));
	    	 structures.add(new Structure(-10000, -10000 , bloqueTam, bloqueTam, N1F2PA3,0,powerups));
	    	 structures.add(new Structure(-10000, -10000 , bloqueTam, bloqueTam, N1F2PA3,0,powerups));
	    	 structures.add(new Structure(-10000, -10000 , bloqueTam, bloqueTam, N1F2PA3,0,powerups));
	    	 structures.add(new Structure(-10000, -10000 , bloqueTam, bloqueTam, N1F2PA3,0,powerups));
	    	 structures.add(new Structure(-10000, -10000 , bloqueTam, bloqueTam, N1F2PA3,0,powerups));
	    	 
	    	 
	    	 
	    	 
	    	 invalido = 1;
		}
		
		if(Window.frameSeleccionado == 1) {		
		    	 // Modificaciones -----------------------------------
		    	
	    	for(Structure structures1 : structures) {
	    			structures1.width = bloqueTam;
	    			structures1.height = bloqueTam;
	    	}
	    	
	    	// Reasignación de posiciones para la PRIMERA OFICINA
	    	structures.get(0).x = bloqueTam * 2 + offsetX;
	    	structures.get(0).y = bloqueTam * 2 + offsetY;
	    	structures.get(1).x = bloqueTam * 3 + offsetX;
	    	structures.get(1).y = bloqueTam * 2 + offsetY;
	    	structures.get(2).x = bloqueTam * 4 + offsetX;
	    	structures.get(2).y = bloqueTam * 2 + offsetY;
	    	structures.get(3).x = bloqueTam * 2 + offsetX;
	    	structures.get(3).y = bloqueTam * 3 + offsetY;
	    	structures.get(4).x = bloqueTam * 2 + offsetX;
	    	structures.get(4).y = bloqueTam * 4 + offsetY;
	    	structures.get(5).x = bloqueTam * 3 + offsetX;
	    	structures.get(5).y = bloqueTam * 4 + offsetY;
	    	structures.get(6).x = bloqueTam * 4 + offsetX;
	    	structures.get(6).y = bloqueTam * 4 + offsetY;
	    	structures.get(7).x = bloqueTam * 4 + offsetX;
	    	structures.get(7).y = bloqueTam * 3 + offsetY;
	    	structures.get(8).x = bloqueTam * 3 + offsetX;
	    	structures.get(8).y = bloqueTam * 3 + offsetY;

	    	// Reasignación de posiciones para la SEGUNDA OFICINA
	    	structures.get(9).x = bloqueTam * 7 + offsetX;
	    	structures.get(9).y = bloqueTam * 2 + offsetY;
	    	structures.get(10).x = bloqueTam * 8 + offsetX;
	    	structures.get(10).y = bloqueTam * 2 + offsetY;
	    	structures.get(11).x = bloqueTam * 9 + offsetX;
	    	structures.get(11).y = bloqueTam * 2 + offsetY;
	    	structures.get(12).x = bloqueTam * 7 + offsetX;
	    	structures.get(12).y = bloqueTam * 3 + offsetY;
	    	structures.get(13).x = bloqueTam * 7 + offsetX;
	    	structures.get(13).y = bloqueTam * 4 + offsetY;
	    	structures.get(14).x = bloqueTam * 8 + offsetX;
	    	structures.get(14).y = bloqueTam * 4 + offsetY;
	    	structures.get(15).x = bloqueTam * 9 + offsetX;
	    	structures.get(15).y = bloqueTam * 4 + offsetY;
	    	structures.get(16).x = bloqueTam * 9 + offsetX;
	    	structures.get(16).y = bloqueTam * 3 + offsetY;
	    	structures.get(17).x = bloqueTam * 8 + offsetX;
	    	structures.get(17).y = bloqueTam * 3 + offsetY;

	    	// Reasignación de posiciones para la TERCERA OFICINA
	    	structures.get(18).x = bloqueTam * 2 + offsetX;
	    	structures.get(18).y = bloqueTam * 6 + offsetY;
	    	structures.get(19).x = bloqueTam * 3 + offsetX;
	    	structures.get(19).y = bloqueTam * 6 + offsetY;
	    	structures.get(20).x = bloqueTam * 4 + offsetX;
	    	structures.get(20).y = bloqueTam * 6 + offsetY;
	    	structures.get(21).x = bloqueTam * 2 + offsetX;
	    	structures.get(21).y = bloqueTam * 7 + offsetY;
	    	structures.get(22).x = bloqueTam * 2 + offsetX;
	    	structures.get(22).y = bloqueTam * 8 + offsetY;
	    	structures.get(23).x = bloqueTam * 3 + offsetX;
	    	structures.get(23).y = bloqueTam * 8 + offsetY;
	    	structures.get(24).x = bloqueTam * 4 + offsetX;
	    	structures.get(24).y = bloqueTam * 8 + offsetY;
	    	structures.get(25).x = bloqueTam * 4 + offsetX;
	    	structures.get(25).y = bloqueTam * 7 + offsetY;
	    	structures.get(26).x = bloqueTam * 3 + offsetX;
	    	structures.get(26).y = bloqueTam * 7 + offsetY;

	    	// Reasignación de posiciones para la CUARTA OFICINA
	    	structures.get(27).x = bloqueTam * 7 + offsetX;
	    	structures.get(27).y = bloqueTam * 6 + offsetY;
	    	structures.get(28).x = bloqueTam * 8 + offsetX;
	    	structures.get(28).y = bloqueTam * 6 + offsetY;
	    	structures.get(29).x = bloqueTam * 9 + offsetX;
	    	structures.get(29).y = bloqueTam * 6 + offsetY;
	    	structures.get(30).x = bloqueTam * 7 + offsetX;
	    	structures.get(30).y = bloqueTam * 7 + offsetY;
	    	structures.get(31).x = bloqueTam * 7 + offsetX;
	    	structures.get(31).y = bloqueTam * 8 + offsetY;
	    	structures.get(32).x = bloqueTam * 8 + offsetX;
	    	structures.get(32).y = bloqueTam * 8 + offsetY;
	    	structures.get(33).x = bloqueTam * 9 + offsetX;
	    	structures.get(33).y = bloqueTam * 8 + offsetY;
	    	structures.get(34).x = bloqueTam * 9 + offsetX;
	    	structures.get(34).y = bloqueTam * 7 + offsetY;
	    	structures.get(35).x = bloqueTam * 8 + offsetX;
	    	structures.get(35).y = bloqueTam * 7 + offsetY;

	    	// Reasignación de posiciones para las estructuras adicionales
	    	structures.get(36).x = bloqueTam * 1 + offsetX;
	    	structures.get(36).y = bloqueTam * 3 + offsetY;
	    	structures.get(37).x = bloqueTam * 1 + offsetX;
	    	structures.get(37).y = bloqueTam * 7 + offsetY;
	    	structures.get(38).x = bloqueTam * 6 + offsetX;
	    	structures.get(38).y = bloqueTam * 1 + offsetY;
	    	structures.get(39).x = bloqueTam * 13 + offsetX;
	    	structures.get(39).y = bloqueTam * 8 + offsetY;

	    	// Reasignación de posiciones para OFICINAS LATERALES
	    	structures.get(40).x = bloqueTam * 11 + offsetX;
	    	structures.get(40).y = bloqueTam * 1 + offsetY;
	    	structures.get(41).x = bloqueTam * 12 + offsetX;
	    	structures.get(41).y = bloqueTam * 1 + offsetY;
	    	structures.get(42).x = bloqueTam * 13 + offsetX;
	    	structures.get(42).y = bloqueTam * 1 + offsetY;
	    	structures.get(43).x = bloqueTam * 11 + offsetX;
	    	structures.get(43).y = bloqueTam * 3 + offsetY;
	    	structures.get(44).x = bloqueTam * 12 + offsetX;
	    	structures.get(44).y = bloqueTam * 3 + offsetY;
	    	structures.get(45).x = bloqueTam * 13 + offsetX;
	    	structures.get(45).y = bloqueTam * 3 + offsetY;
	    	structures.get(46).x = bloqueTam * 11 + offsetX;
	    	structures.get(46).y = bloqueTam * 5 + offsetY;
	    	structures.get(47).x = bloqueTam * 12 + offsetX;
	    	structures.get(47).y = bloqueTam * 5 + offsetY;
	    	structures.get(48).x = bloqueTam * 13 + offsetX;
	    	structures.get(48).y = bloqueTam * 5 + offsetY;
	    	structures.get(49).x = bloqueTam * 11 + offsetX;
	    	structures.get(49).y = bloqueTam * 7 + offsetY;
	    	structures.get(50).x = bloqueTam * 12 + offsetX;
	    	structures.get(50).y = bloqueTam * 7 + offsetY;
	    	structures.get(51).x = bloqueTam * 13 + offsetX;
	    	structures.get(51).y = bloqueTam * 7 + offsetY;

	    	
	    	
		    	 // PRIMERA OFICINA. 
		    	 structures.get(0).image = N1F1PA9 ;   // ---->
		    	 structures.get(1).image = N1F1PU ;
		    	 structures.get(2).image = N1F1PA8 ;
		    	 structures.get(3).image = N1F1PL ;       	  //  	   |
		    	 structures.get(4).image = N1F1PA7 ;			//	   ‿ 	
		    	 structures.get(5).image = N1F1PD ;  	  // ---->       
		    	 structures.get(6).image = N1F1PA6 ;
		    	 structures.get(7).image = N1F1PR ;
		    	 structures.get(8).image = N1F1INTERIOR ;
		    
		    	 // SEGUNDA OFICINA. 
		    	 structures.get(9).image = N1F1PA9 ;
		    	 structures.get(10).image = N1F1PU ;
		    	 structures.get(11).image = N1F1PA8 ;
		    	 structures.get(12).image = N1F1PL ;       	  //  	   |
		    	 structures.get(13).image = N1F1PA7 ;
		    	 structures.get(14).image = N1F1PD ;  	  // ---->       
		    	 structures.get(15).image = N1F1PA6 ;
		    	 structures.get(16).image = N1F1PR ;
		    	 structures.get(17).image = N1F1INTERIOR ;
		    	 
		    	 // TERCERA OFICINA. 
		    	 structures.get(18).image = N1F1PA9 ;   // ---->
		    	 structures.get(19).image = N1F1PU ;
		    	 structures.get(20).image = N1F1PA8 ;
		    	 structures.get(21).image = N1F1PL ;       	  //  	   |
		    	 structures.get(22).image = N1F1PA7 ;			//	   ‿ 	
		    	 structures.get(23).image = N1F1PD ;  	  
		    	 structures.get(24).image = N1F1PA6 ;
		    	 structures.get(25).image = N1F1PR ;
		    	 structures.get(26).image = N1F1INTERIOR ;
		    	 
		    	 // CUARTA OFICINA.
		    	 structures.get(27).image = N1F1PA9 ;
		    	 structures.get(28).image = N1F1PU ;
		    	 structures.get(29).image = N1F1PA8 ;
		    	 structures.get(30).image = N1F1PL ;       	  //  	   |
		    	 structures.get(31).image = N1F1PA7 ;
		    	 structures.get(32).image = N1F1PD ;  	  // ---->       
		    	 structures.get(33).image = N1F1PA6 ;
		    	 structures.get(34).image = N1F1PR ;
		    	 structures.get(35).image = N1F1INTERIOR ;
		    	
		    	 // OBJETOS
		    	 structures.get(36).image = impresora ; // Impresora
		    	 structures.get(37).image = impresora ; // Impresora
		    	 structures.get(38).image = trapeador ; // Plantas
		    	 structures.get(39).image = planta ; // Planta
			    
			    // ------------------------------------------------------ Condiciones para pasar al siguiente frame
		    	 if (iPressed && canPress) {
		    		    // Restablece inmediatamente iPressed a false
		    		    iPressed = false;
		    		    
		    		    // Ejecuta el código que necesitas
		    		    Window.frameSeleccionado++;
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

		    		    // Bloquea la activación de iPressed por un segundo
		    		    canPress = false;
		    		    lastPressedTime = System.currentTimeMillis();
		    		}

		    		// Verifica si ha pasado un segundo para permitir presionar nuevamente
		    		if (!canPress && System.currentTimeMillis() - lastPressedTime >= 1000) {
		    		    canPress = true;
		    		}
			    // ------------------------------------------------------ Fin de Condiciones para pasar al siguiente frame
		}   
		    
		else if(Window.frameSeleccionado == 2) {

	    	for(Structure structures1 : structures) {
    			structures1.width = bloqueTam;
    			structures1.height = bloqueTam;
	    	}
			
		    structures.get(0).x = bloqueTam * 3 + offsetX;
		    structures.get(0).y = bloqueTam * 2 + offsetY;
		    structures.get(0).image = N1F2PA3;

		    structures.get(1).x = bloqueTam * 3 + offsetX;
		    structures.get(1).y = bloqueTam * 3 + offsetY;
		    structures.get(1).image = N1F2PA3;

		    structures.get(2).x = bloqueTam * 3 + offsetX;
		    structures.get(2).y = bloqueTam * 4 + offsetY;
		    structures.get(2).image = N1F2PA3;

		    structures.get(3).x = bloqueTam * 4 + offsetX;
		    structures.get(3).y = bloqueTam * 3 + offsetY;
		    structures.get(3).image = N1F2PA3;

		    structures.get(4).x = bloqueTam * 4 + offsetX;
		    structures.get(4).y = bloqueTam * 4 + offsetY;
		    structures.get(4).image = N1F2PA3;

		    structures.get(5).x = bloqueTam * 5 + offsetX;
		    structures.get(5).y = bloqueTam * 2 + offsetY;
		    structures.get(5).image = N1F2PA3;

		    structures.get(6).x = bloqueTam * 5 + offsetX;
		    structures.get(6).y = bloqueTam * 4 + offsetY;
		    structures.get(6).image = N1F2PA3;

		    structures.get(7).x = bloqueTam * 1 + offsetX;
		    structures.get(7).y = bloqueTam * 6 + offsetY;
		    structures.get(7).image = N1F2PA3;

		    structures.get(8).x = bloqueTam * 2 + offsetX;
		    structures.get(8).y = bloqueTam * 6 + offsetY;
		    structures.get(8).image = N1F2PA3;

		    structures.get(9).x = bloqueTam * 7 + offsetX;
		    structures.get(9).y = bloqueTam * 2 + offsetY;
		    structures.get(9).image = N1F2PA3;

		    structures.get(10).x = bloqueTam * 7 + offsetX;
		    structures.get(10).y = bloqueTam * 3 + offsetY;
		    structures.get(10).image = N1F2PA3;

		    structures.get(11).x = bloqueTam * 7 + offsetX;
		    structures.get(11).y = bloqueTam * 4 + offsetY;
		    structures.get(11).image = N1F2PA3;

		    structures.get(12).x = bloqueTam * 8 + offsetX;
		    structures.get(12).y = bloqueTam * 2 + offsetY;
		    structures.get(12).image = N1F2PA3;

		    structures.get(13).x = bloqueTam * 10 + offsetX;
		    structures.get(13).y = bloqueTam * 2 + offsetY;
		    structures.get(13).image = N1F2PA3;

		    structures.get(14).x = bloqueTam * 10 + offsetX;
		    structures.get(14).y = bloqueTam * 3 + offsetY;
		    structures.get(14).image = N1F2PA3;

		    structures.get(15).x = bloqueTam * 12 + offsetX;
		    structures.get(15).y = bloqueTam * 2 + offsetY;
		    structures.get(15).image = N1F2PA3;

		    structures.get(16).x = bloqueTam * 12 + offsetX;
		    structures.get(16).y = bloqueTam * 3 + offsetY;
		    structures.get(16).image = N1F2PA3;

		    structures.get(17).x = bloqueTam * 12 + offsetX;
		    structures.get(17).y = bloqueTam * 5 + offsetY;
		    structures.get(17).image = N1F2PA3;

		    structures.get(18).x = bloqueTam * 10 + offsetX;
		    structures.get(18).y = bloqueTam * 5 + offsetY;
		    structures.get(18).image = N1F2PA3;

		    structures.get(19).x = bloqueTam * 8 + offsetX;
		    structures.get(19).y = bloqueTam * 5 + offsetY;
		    structures.get(19).image = N1F2PA3;

		    structures.get(20).x = bloqueTam * 11 + offsetX;
		    structures.get(20).y = bloqueTam * 6 + offsetY;
		    structures.get(20).image = N1F2PA3;

		    structures.get(21).x = bloqueTam * 4 + offsetX;
		    structures.get(21).y = bloqueTam * 6 + offsetY;
		    structures.get(21).image = N1F2PA3;

		    structures.get(22).x = bloqueTam * 6 + offsetX;
		    structures.get(22).y = bloqueTam * 6 + offsetY;
		    structures.get(22).image = N1F2PA3;

		    structures.get(23).x = bloqueTam * 7 + offsetX;
		    structures.get(23).y = bloqueTam * 6 + offsetY;
		    structures.get(23).image = N1F2PA3;

		    structures.get(24).x = bloqueTam * 8 + offsetX;
		    structures.get(24).y = bloqueTam * 6 + offsetY;
		    structures.get(24).image = N1F2PA3;

		    structures.get(25).x = bloqueTam * 7 + offsetX;
		    structures.get(25).y = bloqueTam * 7 + offsetY;
		    structures.get(25).image = N1F2PA3;

		    structures.get(26).x = bloqueTam * 1 + offsetX;
		    structures.get(26).y = bloqueTam * 8 + offsetY;
		    structures.get(26).image = N1F2PA3;

		    structures.get(27).x = bloqueTam * 4 + offsetX;
		    structures.get(27).y = bloqueTam * 8 + offsetY;
		    structures.get(27).image = N1F2PA3;

		    structures.get(28).x = bloqueTam * 5 + offsetX;
		    structures.get(28).y = bloqueTam * 8 + offsetY;
		    structures.get(28).image = N1F2PA3;

		    structures.get(29).x = bloqueTam * 9 + offsetX;
		    structures.get(29).y = bloqueTam * 8 + offsetY;
		    structures.get(29).image = N1F2PA3;

		    structures.get(30).x = bloqueTam * 1 + offsetX;
		    structures.get(30).y = bloqueTam * 8 + offsetY;
		    structures.get(30).image = N1F2PA3;
		    
		    structures.get(31).x = bloqueTam * 12 + offsetX;
		    structures.get(31).y = bloqueTam * 3 + offsetY;
		    structures.get(31).image = N1F2PA3;
		    
		    structures.get(32).x = bloqueTam * 12 + offsetX;
		    structures.get(32).y = bloqueTam * 5 + offsetY;
		    structures.get(32).image = N1F2PA3;
		    
		    structures.get(33).x = bloqueTam * 9 + offsetX;
		    structures.get(33).y = bloqueTam * 8 + offsetY;
		    structures.get(33).image = N1F2PA3;
		    
		    structures.get(33).x = bloqueTam * 11 + offsetX;
		    structures.get(33).y = bloqueTam * 8 + offsetY;
		    structures.get(33).image = N1F2PA3;
		    
		    structures.get(34).x = bloqueTam * 13 + offsetX;
		    structures.get(34).y = bloqueTam * 8 + offsetY;
		    structures.get(34).image = N1F2PA3;
		    
		    // LATAS
		    
		    structures.get(35).x = bloqueTam * 2 + offsetX;
		    structures.get(35).y = bloqueTam * 3 + offsetY;
		    structures.get(35).tipo = 1;
		    structures.get(35).image = latas;
		    
		    structures.get(36).x = bloqueTam * 2 + offsetX;
		    structures.get(36).y = bloqueTam * 2 + offsetY;
		    structures.get(36).tipo = 1;
		    structures.get(36).image = latas;
		    
		    structures.get(37).x = bloqueTam * 2 + offsetX;
		    structures.get(37).y = bloqueTam * 4+ offsetY;
		    structures.get(37).tipo = 1;
		    structures.get(37).image = latas;
		    
		    structures.get(38).x = bloqueTam * 1 + offsetX;
		    structures.get(38).y = bloqueTam * 4+ offsetY;
		    structures.get(38).tipo = 1;
		    structures.get(38).image = latas;
		    
		    structures.get(39).x = bloqueTam * 4 + offsetX;
		    structures.get(39).y = bloqueTam * 1+ offsetY;
		    structures.get(39).tipo = 1;
		    structures.get(39).image = latas;
		    
		    structures.get(40).x = bloqueTam * 5 + offsetX;
		    structures.get(40).y = bloqueTam * 1+ offsetY;
		    structures.get(40).tipo = 1;
		    structures.get(40).image = latas;
		    
		    structures.get(41).x = bloqueTam * 6 + offsetX;
		    structures.get(41).y = bloqueTam * 3+ offsetY;
		    structures.get(41).tipo = 1;
		    structures.get(41).image = latas;
		    
		    structures.get(42).x = bloqueTam * 3 + offsetX;
		    structures.get(42).y = bloqueTam * 5+ offsetY;
		    structures.get(42).tipo = 1;
		    structures.get(42).image = latas;
		    
		    structures.get(43).x = bloqueTam * 4 + offsetX;
		    structures.get(43).y = bloqueTam * 5+ offsetY;
		    structures.get(43).tipo = 1;
		    structures.get(43).image = latas;
		    
		    structures.get(44).x = bloqueTam * 5 + offsetX;
		    structures.get(44).y = bloqueTam * 5+ offsetY;
		    structures.get(44).tipo = 1;
		    structures.get(44).image = latas;
		    
		    structures.get(45).x = bloqueTam * 5 + offsetX;
		    structures.get(45).y = bloqueTam * 7+ offsetY;
		    structures.get(45).tipo = 1;
		    structures.get(45).image = latas;
		    
		    structures.get(46).x = bloqueTam * 1 + offsetX;
		    structures.get(46).y = bloqueTam * 7+ offsetY;
		    structures.get(46).tipo = 1;
		    structures.get(46).image = latas;
		    
		    structures.get(47).x = bloqueTam * 2 + offsetX;
		    structures.get(47).y = bloqueTam * 8+ offsetY;
		    structures.get(47).tipo = 1;
		    structures.get(47).image = latas;
		    
		    structures.get(48).x = bloqueTam * 7 + offsetX;
		    structures.get(48).y = bloqueTam * 8+ offsetY;
		    structures.get(48).tipo = 1;
		    structures.get(48).image = latas;
		    
		    structures.get(49).x = bloqueTam * 8 + offsetX;
		    structures.get(49).y = bloqueTam * 8+ offsetY;
		    structures.get(49).tipo = 1;
		    structures.get(49).image = latas;
		    
		    structures.get(50).x = bloqueTam * 10 + offsetX;
		    structures.get(50).y = bloqueTam * 8+ offsetY;
		    structures.get(50).tipo = 1;
		    structures.get(50).image = latas;
		    
		    structures.get(51).x = bloqueTam * 12 + offsetX;
		    structures.get(51).y = bloqueTam * 8+ offsetY;
		    structures.get(51).tipo = 1;
		    structures.get(51).image = latas;
		    
		    structures.get(52).x = bloqueTam * 11 + offsetX;
		    structures.get(52).y = bloqueTam * 7+ offsetY;
		    structures.get(52).tipo = 1;
		    structures.get(52).image = latas;
		    
		    structures.get(53).x = bloqueTam * 10 + offsetX;
		    structures.get(53).y = bloqueTam * 6+ offsetY;
		    structures.get(53).tipo = 1;
		    structures.get(53).image = latas; 

		    structures.get(54).x = bloqueTam * 9 + offsetX;
		    structures.get(54).y = bloqueTam * 5+ offsetY;
		    structures.get(54).tipo = 1;
		    structures.get(54).image = latas; 
		    
		    structures.get(55).x = bloqueTam * 10 + offsetX;
		    structures.get(55).y = bloqueTam * 4 + offsetY;
		    structures.get(55).tipo = 1;
		    structures.get(55).image = latas; 
		    
		    structures.get(56).x = bloqueTam * 11 + offsetX;
		    structures.get(56).y = bloqueTam * 2 + offsetY;
		    structures.get(56).tipo = 1;
		    structures.get(56).image = latas; 

		    structures.get(57).x = bloqueTam * 9 + offsetX;
		    structures.get(57).y = bloqueTam * 1 + offsetY;
		    structures.get(57).tipo = 1;
		    structures.get(57).image = latas; 
		    
		    structures.get(58).x = bloqueTam * 8 + offsetX;
		    structures.get(58).y = bloqueTam * 1 + offsetY;
		    structures.get(58).tipo = 1;
		    structures.get(58).image = latas; 
		   
		    structures.get(59).x = bloqueTam * 13 + offsetX;
		    structures.get(59).y = bloqueTam * 1+ offsetY;
		    structures.get(59).tipo = 1;
		    structures.get(59).image = latas; 
		    
		    structures.get(60).x = bloqueTam * 13 + offsetX;
		    structures.get(60).y = bloqueTam * 2+ offsetY;
		    structures.get(60).tipo = 1;
		    structures.get(60).image = latas; 
		    
		    structures.get(61).x = bloqueTam * 12 + offsetX;
		    structures.get(61).y = bloqueTam * 4+ offsetY;
		    structures.get(61).tipo = 1;
		    structures.get(61).image = latas; 
		    
		    structures.get(62).x = bloqueTam * 13 + offsetX;
		    structures.get(62).y = bloqueTam * 7+ offsetY;
		    structures.get(62).tipo = 1;
		    structures.get(62).image = latas; 
		    
		    structures.get(63).x = bloqueTam * 13 + offsetX;
		    structures.get(63).y = bloqueTam * 6+ offsetY;
		    structures.get(63).tipo = 1;
		    structures.get(63).image = latas; 
		    
		    structures.get(64).x = bloqueTam * 13 + offsetX;
		    structures.get(64).y = bloqueTam * 5+ offsetY;
		    structures.get(64).tipo = 1;
		    structures.get(64).image = latas; 
		    
		    structures.get(65).x = bloqueTam * 8 + offsetX;
		    structures.get(65).y = bloqueTam * 3+ offsetY;
		    structures.get(65).tipo = 1;
		    structures.get(65).image = latas; 
		    
		    structures.get(66).x = bloqueTam * 8 + offsetX;
		    structures.get(66).y = bloqueTam * 4+ offsetY;
		    structures.get(66).tipo = 1;
		    structures.get(66).image = latas; 
		    
		    
		    // ------------------------------------------------------ Condiciones para pasar al siguiente frame
	    	 if (iPressed && canPress) {
	    		    // Restablece inmediatamente iPressed a false
	    		    iPressed = false;
	    		    
	    		    // Ejecuta el código que necesitas
	    		    Window.frameSeleccionado++;
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
	    		    // Bloquea la activación de iPressed por un segundo
	    		    canPress = false;
	    		    lastPressedTime = System.currentTimeMillis();
	    		}

	    		// Verifica si ha pasado un segundo para permitir presionar nuevamente
	    		if (!canPress && System.currentTimeMillis() - lastPressedTime >= 1000) {
	    		    canPress = true;
	    		}
		    // ------------------------------------------------------ Fin de Condiciones para pasar al siguiente frame
		}
		    else if(Window.frameSeleccionado == 3) {
		    	
		    	for(Structure structures1 : structures) {
	    			structures1.width = bloqueTam;
	    			structures1.height = bloqueTam;
		    	}
		    	
		    	
		    	structures.get(1).x = bloqueTam * 2 + offsetX;
			    structures.get(1).y = bloqueTam * 2+ offsetY;
			    structures.get(1).image = N1F3PA;
		    	
			    structures.get(2).x = bloqueTam * 3 + offsetX;
			    structures.get(2).y = bloqueTam * 2+ offsetY;
			    structures.get(2).image = N1F3PA;
			    
			    structures.get(3).x = bloqueTam * 4 + offsetX;
			    structures.get(3).y = bloqueTam * 2+ offsetY;
			    structures.get(3).image = N1F3PA;
		    	
			    structures.get(4).x = bloqueTam * 6 + offsetX;
			    structures.get(4).y = bloqueTam * 2+ offsetY;
			    structures.get(4).image = N1F3PA;
			    
			    structures.get(5).x = bloqueTam * 8 + offsetX;
			    structures.get(5).y = bloqueTam * 2+ offsetY;
			    structures.get(5).image = N1F3PA;

			    structures.get(6).x = bloqueTam * 8 + offsetX;
			    structures.get(6).y = bloqueTam * 2+ offsetY;
			    structures.get(6).image = N1F3PA;
		    	
			    structures.get(7).x = bloqueTam * 10 + offsetX;
			    structures.get(7).y = bloqueTam * 2+ offsetY;
			    structures.get(7).image = N1F3PA;
			    
			    structures.get(8).x = bloqueTam * 11 + offsetX;
			    structures.get(8).y = bloqueTam * 2+ offsetY;
			    structures.get(8).image = N1F3PA;
			    
			    structures.get(9).x = bloqueTam * 12 + offsetX;
			    structures.get(9).y = bloqueTam * 2+ offsetY;
			    structures.get(9).image = N1F3PA;
			    
			    structures.get(10).x = bloqueTam * 12 + offsetX;
			    structures.get(10).y = bloqueTam * 3+ offsetY;
			    structures.get(10).image = N1F3PA;
			    
			    structures.get(11).x = bloqueTam * 10 + offsetX;
			    structures.get(11).y = bloqueTam * 3+ offsetY;
			    structures.get(11).image = N1F3PA;
			    
			    structures.get(12).x = bloqueTam * 4 + offsetX;
			    structures.get(12).y = bloqueTam * 4+ offsetY;
			    structures.get(12).image = N1F3PA;
			    
			    structures.get(13).x = bloqueTam * 2 + offsetX;
			    structures.get(13).y = bloqueTam * 4+ offsetY;
			    structures.get(13).image = N1F3PA;
			    
			    structures.get(14).x = bloqueTam * 3 + offsetX;
			    structures.get(14).y = bloqueTam * 5+ offsetY;
			    structures.get(14).image = N1F3PA;
			    
			    structures.get(15).x = bloqueTam * 6 + offsetX;
			    structures.get(15).y = bloqueTam * 4+ offsetY;
			    structures.get(15).image = N1F3PA;
			    
			    structures.get(16).x = bloqueTam * 8 + offsetX;
			    structures.get(16).y = bloqueTam * 4+ offsetY;
			    structures.get(16).image = N1F3PA;
			    
			    structures.get(17).x = bloqueTam * 10 + offsetX;
			    structures.get(17).y = bloqueTam * 4+ offsetY;
			    structures.get(17).image = N1F3PA;
			    
			    structures.get(18).x = bloqueTam * 2 + offsetX;
			    structures.get(18).y = bloqueTam * 8+ offsetY;
			    structures.get(18).image = N1F3PA;
			    
			    structures.get(19).x = bloqueTam * 10 + offsetX;
			    structures.get(19).y = bloqueTam * 4+ offsetY;
			    structures.get(19).image = N1F3PA;
			    
			    structures.get(20).x = bloqueTam * 5 + offsetX;
			    structures.get(20).y = bloqueTam * 8+ offsetY;
			    structures.get(20).image = N1F3PA;
			    
			    structures.get(21).x = bloqueTam * 7 + offsetX;
			    structures.get(21).y = bloqueTam * 8+ offsetY;
			    structures.get(21).image = N1F3PA;
			    
			    structures.get(22).x = bloqueTam * 9 + offsetX;
			    structures.get(22).y = bloqueTam * 7+ offsetY;
			    structures.get(22).image = N1F3PA;
			    
			    structures.get(23).x = bloqueTam * 10 + offsetX;
			    structures.get(23).y = bloqueTam * 7+ offsetY;
			    structures.get(23).image = N1F3PA;
			    
			    structures.get(24).x = bloqueTam * 12 + offsetX;
			    structures.get(24).y = bloqueTam * 8+ offsetY;
			    structures.get(24).image = N1F3PA;
			    
			    structures.get(25).x = bloqueTam * 13 + offsetX;
			    structures.get(25).y = bloqueTam * 8+ offsetY;
			    structures.get(25).image = N1F3PA;
			    
			    structures.get(26).x = bloqueTam * 12 + offsetX;
			    structures.get(26).y = bloqueTam * 6+ offsetY;
			    structures.get(26).image = N1F3PA;
			    
			    structures.get(27).x = bloqueTam * 12 + offsetX;
			    structures.get(27).y = bloqueTam * 6+ offsetY;
			    structures.get(27).image = N1F3PA;
		    
			    structures.get(28).x = bloqueTam * 12 + offsetX;
			    structures.get(28).y = bloqueTam * 5+ offsetY;
			    structures.get(28).image = N1F3PA;
			    
			    structures.get(29).x = bloqueTam * 13 + offsetX;
			    structures.get(29).y = bloqueTam * 5+ offsetY;
			    structures.get(29).image = N1F3PA;
			    
			    structures.get(30).x = bloqueTam * 10 + offsetX;
			    structures.get(30).y = bloqueTam * 6+ offsetY;
			    structures.get(30).image = N1F3PA;
			    
			    structures.get(31).x = bloqueTam * 13 + offsetX;
			    structures.get(31).y = bloqueTam * 5+ offsetY;
			    structures.get(31).image = N1F3PA;
			    
			    structures.get(32).x = bloqueTam * 9 + offsetX;
			    structures.get(32).y = bloqueTam * 7+ offsetY;
			    structures.get(32).image = N1F3PA;
			    
			    structures.get(33).x = bloqueTam * 2 + offsetX;
			    structures.get(33).y = bloqueTam * 6+ offsetY;
			    structures.get(33).image = N1F3PA;
			    
			    structures.get(34).x = bloqueTam * 3 + offsetX;
			    structures.get(34).y = bloqueTam * 6+ offsetY;
			    structures.get(34).image = N1F3PA;
			    
			    structures.get(35).x = bloqueTam * 6 + offsetX;
			    structures.get(35).y = bloqueTam * 6+ offsetY;
			    structures.get(35).tipo = 0;
			    structures.get(35).image = N1F3PA;
			    
			    structures.get(36).x = bloqueTam * 7 + offsetX;
			    structures.get(36).y = bloqueTam * 6+ offsetY;
			    structures.get(36).tipo = 0;
			    structures.get(36).image = N1F3PA;
			    
			    structures.get(37).x = bloqueTam * 8 + offsetX;
			    structures.get(37).y = bloqueTam * 6+ offsetY;
			    structures.get(37).tipo = 0;
			    structures.get(37).image = N1F3PA;
			    
			    structures.get(38).x = bloqueTam * 8 + offsetX;
			    structures.get(38).y = bloqueTam * 5+ offsetY;
			    structures.get(38).tipo = 0;
			    structures.get(38).image = N1F3PA;
			    
			    
			    
			    
			    
			    
		    	// cajeros
		    	structures.get(0).x = bloqueTam * 3 + offsetX;
			    structures.get(0).y = bloqueTam * 1+ offsetY;
			    structures.get(0).tipo = 1;
			    structures.get(0).image = cajero;
			    
			    structures.get(39).x = bloqueTam * 8 + offsetX;
			    structures.get(39).y = bloqueTam * 1+ offsetY;
			    structures.get(39).image = cajero;
			    
			    structures.get(40).x = bloqueTam * 10 + offsetX;
			    structures.get(40).y = bloqueTam * 1+ offsetY;
			    structures.get(40).image = cajero;
			    
			    structures.get(41).x = bloqueTam * 7 + offsetX;
			    structures.get(41).y = bloqueTam * 2+ offsetY;
			    structures.get(41).image = cajero;
			    
			    structures.get(42).x = bloqueTam * 9 + offsetX;
			    structures.get(42).y = bloqueTam * 2+ offsetY;
			    structures.get(42).image = cajero;
			    
			    structures.get(43).x = bloqueTam * 8 + offsetX;
			    structures.get(43).y = bloqueTam * 3+ offsetY;
			    structures.get(43).image = cajero;
			    
			    structures.get(44).x = bloqueTam * 9 + offsetX;
			    structures.get(44).y = bloqueTam * 4+ offsetY;
			    structures.get(44).image = cajero;
			    
			    structures.get(45).x = bloqueTam * 7 + offsetX;
			    structures.get(45).y = bloqueTam * 4+ offsetY;
			    structures.get(45).image = cajero;
			    
			    structures.get(46).x = bloqueTam * 5 + offsetX;
			    structures.get(46).y = bloqueTam * 4+ offsetY;
			    structures.get(46).image = cajero;
			    
			    structures.get(47).x = bloqueTam * 1 + offsetX;
			    structures.get(47).y = bloqueTam * 3+ offsetY;
			    structures.get(47).image = cajero;
			    
			    structures.get(48).x = bloqueTam * 2 + offsetX;
			    structures.get(48).y = bloqueTam * 3+ offsetY;
			    structures.get(48).image = cajero;
		    	
			    structures.get(49).x = bloqueTam * 5 + offsetX;
			    structures.get(49).y = bloqueTam * 3+ offsetY;
			    structures.get(49).image = cajero;
			    
			    structures.get(50).x = bloqueTam * 1 + offsetX;
			    structures.get(50).y = bloqueTam * 6+ offsetY;
			    structures.get(50).image = cajero;
			    
			    structures.get(51).x = bloqueTam * 4 + offsetX;
			    structures.get(51).y = bloqueTam * 6+ offsetY;
			    structures.get(51).image = cajero;

			    structures.get(52).x = bloqueTam * 4 + offsetX;
			    structures.get(52).y = bloqueTam * 6+ offsetY;
			    structures.get(52).image = cajero;
			    
			    structures.get(53).x = bloqueTam * 4 + offsetX;
			    structures.get(53).y = bloqueTam * 6+ offsetY;
			    structures.get(53).image = cajero;
			    
			    structures.get(54).x = bloqueTam * 5 + offsetX;
			    structures.get(54).y = bloqueTam * 6+ offsetY;
			    structures.get(54).image = cajero;
			    
			    structures.get(55).x = bloqueTam * 3 + offsetX;
			    structures.get(55).y = bloqueTam * 7+ offsetY;
			    structures.get(55).image = cajero;
			    
			    structures.get(56).x = bloqueTam * 3 + offsetX;
			    structures.get(56).y = bloqueTam * 8+ offsetY;
			    structures.get(56).image = cajero;
			    
			    structures.get(57).x = bloqueTam * 6 + offsetX;
			    structures.get(57).y = bloqueTam * 8+ offsetY;
			    structures.get(57).image = cajero;
			    
			    structures.get(58).x = bloqueTam * 6 + offsetX;
			    structures.get(58).y = bloqueTam * 7+ offsetY;
			    structures.get(58).image = cajero;
			    
			    structures.get(59).x = bloqueTam * 8 + offsetX;
			    structures.get(59).y = bloqueTam * 8+ offsetY;
			    structures.get(59).image = cajero;
			    
			    structures.get(60).x = bloqueTam * 11 + offsetX;
			    structures.get(60).y = bloqueTam * 8+ offsetY;
			    structures.get(60).image = cajero;
			    
			    structures.get(61).x = bloqueTam * 11 + offsetX;
			    structures.get(61).y = bloqueTam * 5+ offsetY;
			    structures.get(61).image = cajero;
			    
			    structures.get(62).x = bloqueTam * 12 + offsetX;
			    structures.get(62).y = bloqueTam * 4+ offsetY;
			    structures.get(62).image = cajero;
			    
			    structures.get(63).x = bloqueTam * 13 + offsetX;
			    structures.get(63).y = bloqueTam * 4+ offsetY;
			    structures.get(63).image = cajero;
			    
			    structures.get(64).x = bloqueTam * 13 + offsetX;
			    structures.get(64).y = bloqueTam * 3+ offsetY;
			    structures.get(64).image = cajero;
			    
			    structures.get(66).x = bloqueTam * 13 + offsetX;
			    structures.get(66).y = bloqueTam * 7+ offsetY;
			    structures.get(66).image = cajero;
			    
			    
			    // puta maceta
			    
			    structures.get(65).x = bloqueTam * 13 + offsetX;
			    structures.get(65).y = bloqueTam * 1+ offsetY;
			    structures.get(65).image = planta;
			    
			  

			    
		    } 
		    
		    
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
		            	g.drawImage(N1F1ESQ_IZQ_SUP, x, y, bloqueTam, bloqueTam, null);
            		}
            		
            		// Arriba a la derecha
            		else if(j == 0 && i == numColumnas-1) {
		            	g.drawImage(N1F1ESQ_DER_SUP, x, y, bloqueTam, bloqueTam, null);
            		}
            		
            		// Abajo a la derecha
            		else if(j == numFilas-1 && i == numColumnas-1) {
		            	g.drawImage(N1F1ESQ_DER_INF, x, y, bloqueTam, bloqueTam, null);
            		}
            		
            		// Abajo a la izquierda
            		else if(j == numFilas-1 && i == 0) {
		            	g.drawImage(N1F1ESQ_IZQ_INF, x, y, bloqueTam, bloqueTam, null);
            		}
            		
            		// Bordes de arriba 
            		else if(j == 0) {
		            	g.drawImage(N1F1BORDE_ARRIBA, x, y, bloqueTam, bloqueTam, null);		            		
	            	}
	            	
            		// Bordes de abajo
	            	else if(j == numFilas - 1) {
		            	g.drawImage(N1F1BORDE_ABAJO, x, y, bloqueTam, bloqueTam, null);	
	            	}
	            	
            		// Bordes de la izquierda
	            	else if(i == 0) {
	            	    g.drawImage(N1F1BORDE_IZQ, x, y, bloqueTam, bloqueTam, null);
	    
	            	}
	            	
            		// Bordes de la derecha
	            	else if(i == numColumnas - 1) {
	            	    g.drawImage(N1F1BORDE_DER, x, y, bloqueTam, bloqueTam, null);
	            	}

	            		
            		// Suelo
	            	else {
	            		g.drawImage(N1F1SUELO, x, y, bloqueTam, bloqueTam, null);
	            	}
            	}
	            
	            	
	            	if(frameSeleccionado == 2) {
	            		
	            		// Arriba a la izquierda
	            		if(j == 0 && i == 0) {
			            	g.drawImage(N1F2ESQ_IZQ_SUP, x, y, bloqueTam, bloqueTam, null);
	            		}
	            		
	            		// Arriba a la derecha
	            		else if(j == 0 && i == numColumnas-1) {
			            	g.drawImage(N1F2ESQ_DER_SUP, x, y, bloqueTam, bloqueTam, null);
	            		}
	            		
	            		// Abajo a la derecha
	            		else if(j == numFilas-1 && i == numColumnas-1) {
			            	g.drawImage(N1F2ESQ_DER_INF, x, y, bloqueTam, bloqueTam, null);
	            		}
	            		
	            		// Abajo a la izquierda
	            		else if(j == numFilas-1 && i == 0) {
			            	g.drawImage(N1F2ESQ_IZQ_INF, x, y, bloqueTam, bloqueTam, null);
	            		}
	            		
	            		// Bordes de arriba 
	            		else if(j == 0) {
			            	g.drawImage(N1F2BORDE_ARRIBA, x, y, bloqueTam, bloqueTam, null);		            		
		            	}
		            	
	            		// Bordes de abajo
		            	else if(j == numFilas - 1) {
		            	    g.drawImage(N1F2BORDE_ABAJO, x, y, bloqueTam, bloqueTam, null);
		            	}
		            	
	            		// Bordes de la izquierda
		            	else if(i == 0) {
		            	    g.drawImage(N1F2BORDE_IZQ, x, y, bloqueTam, bloqueTam, null);
		            	}
		            	
	            		// Bordes de la derecha
		            	else if(i == numColumnas - 1) {  
		            	    g.drawImage(N1F2BORDE_DER, x, y, bloqueTam, bloqueTam, null);
		            	}

		            		
	            		// Suelo
		            	else {
		            		g.drawImage(N1F2SUELO, x, y, bloqueTam, bloqueTam, null);
		            	} 
	            	}
	            	
	            	
	            	if(frameSeleccionado == 3) {
	            		
	            		// Arriba a la izquierda
	            		if(j == 0 && i == 0) {
			            	g.drawImage(N1F3ESQ_IZQ_SUP, x, y, bloqueTam, bloqueTam, null);
	            		}
	            		
	            		// Arriba a la derecha
	            		else if(j == 0 && i == numColumnas-1) {
			            	g.drawImage(N1F3ESQ_DER_SUP, x, y, bloqueTam, bloqueTam, null);
	            		}
	            		
	            		// Abajo a la derecha
	            		else if(j == numFilas-1 && i == numColumnas-1) {
			            	g.drawImage(N1F3ESQ_DER_INF, x, y, bloqueTam, bloqueTam, null);
	            		}
	            		
	            		// Abajo a la izquierda
	            		else if(j == numFilas-1 && i == 0) {
			            	g.drawImage(N1F3ESQ_IZQ_INF, x, y, bloqueTam, bloqueTam, null);
	            		}
	            		
	            		// Bordes de arriba 
	            		else if(j == 0) {
			            	g.drawImage(N1F3BORDE_ARRIBA, x, y, bloqueTam, bloqueTam, null);		            		
		            	}
		            	
	            		// Bordes de abajo
		            	else if(j == numFilas - 1) {
			            	g.drawImage(N1F3BORDE_ABAJO, x, y, bloqueTam, bloqueTam, null);	
		            	}
		            	
	            		// Bordes de la izquierda
		            	else if(i == 0) {
			            	g.drawImage(N1F3BORDE_IZQ, x, y, bloqueTam, bloqueTam, null);	
		            	}
		            	
	            		// Bordes de la derecha
		            	else if(i == numColumnas - 1) {
			            	g.drawImage(N1F3BORDE_DER, x, y, bloqueTam, bloqueTam, null);	

		            	}
		            		
	            		// Suelo
		            	else {
		            		g.drawImage(N1F3ALFOMBRA, x, y, bloqueTam, bloqueTam, null);
		            	} 
	            		
	            	}
	        }
	    }
	}
}

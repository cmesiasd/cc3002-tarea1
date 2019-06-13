package cc3002.pokemon.fire;

import cc3002.abilities.Attack;
import cc3002.pokemon.fighting.FightingPokemon;
import cc3002.pokemon.lighting.LightingPokemon;
import cc3002.pokemon.psychic.PsychicPokemon;
import cc3002.energyCost.energyCost;
import cc3002.energy.fighting.FightingEnergy;
import cc3002.energy.fire.FireEnergy;
import cc3002.energy.grass.GrassEnergy;
import cc3002.pokemon.grass.GrassPokemon;
import cc3002.energy.lighting.LightingEnergy;
import cc3002.energy.psychic.PsychicEnergy;
import cc3002.energy.water.WaterEnergy;
import cc3002.pokemon.water.WaterPokemon;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class FirePokemonTest {
    FightingEnergy fightingEnergy;
    FightingPokemon fightingPokemon;
    FireEnergy fireEnergy;
    FirePokemon firePokemon;
    GrassEnergy grassEnergy;
    GrassPokemon grassPokemon;
    LightingEnergy lightingEnergy;
    LightingPokemon lightingPokemon;
    PsychicEnergy psychicEnergy;
    PsychicPokemon psychicPokemon, psychicPokemon2;
    WaterEnergy waterEnergy;
    WaterPokemon waterPokemon;
    energyCost At1, At2, At3, At4, At5, At6, At7, IniEnergy, FullEnergy;
    Attack attack1, attack2, attack3, attack4, attack5, attack6, attack7;
    List<Attack> LA_Charmander, LA_Squirtle, LA_Crobat;
    @Before
    public void setUp() throws Exception {
        //region Energies
        psychicEnergy = new PsychicEnergy("psychic");
        waterEnergy = new WaterEnergy("water");
        grassEnergy = new GrassEnergy("grass");
        fireEnergy = new FireEnergy("fire");
        lightingEnergy = new LightingEnergy("lighting");
        fightingEnergy = new FightingEnergy("fighting");
        IniEnergy = new energyCost(0,0,0,0,0,0);
        FullEnergy = new energyCost(10,10,10,10,10,10);
        //endregion

        //region Psychic Pokemon Crobat
        At1 = new energyCost(0,2,0,1,0,0);
        At2 = new energyCost(0,2,1,1,0,0);
        At3 = new energyCost(1,3,0,0,0,0);
        attack1 = new Attack("Vista Nocturna", 40, "Permite robar una carta",At1);
        attack2 = new Attack("Colmillo Ultratoxico", 55, "El pokemon danado pasa a estar envenenado",At2);
        attack3 = new Attack("Golpe Sorpresa", 60, "Hace 10 de dano mas por cada energia tipo Psiquica",At3);
        LA_Crobat = new ArrayList<>();
        LA_Crobat.add(attack1);
        LA_Crobat.add(attack2);
        LA_Crobat.add(attack3);
        psychicPokemon = new PsychicPokemon("Crobat",130,51,IniEnergy,LA_Crobat);
        //endregion

        //region Fire Pokemon Charmander
        At4 = new energyCost(0,0,2,0,0,0);
        At5 = new energyCost(0,0,3,1,0,0);
        attack4 = new Attack("Ascuas", 20, "El pokemon danado descarga una enrgia",At4);
        attack5 = new Attack("Quemadura de lava", 30, "Quema al rival",At5);
        LA_Charmander = new ArrayList<>();
        LA_Charmander.add(attack4);
        LA_Charmander.add(attack5);
        firePokemon = new FirePokemon("Charmander",70,4,FullEnergy,LA_Charmander);
        //endregion

        //region Water Pokemon Squirtle
        At6 = new energyCost(2,0,0,1,0,0);
        At7 = new energyCost(2,0,0,2,0,0);
        attack6 = new Attack("Acua Cola", 45, "Vamoh a calmarnoh",At6);
        attack7 = new Attack("Ataque Caparazon", 70, "Atauqe fisico/aqua",At7);
        LA_Squirtle = new ArrayList<>();
        LA_Squirtle.add(attack6);
        LA_Squirtle.add(attack7);
        waterPokemon = new WaterPokemon("Squirtle",60,7,FullEnergy,LA_Squirtle);
        //endregion
    }

    @Test
    public void attack() {
        firePokemon.attack(psychicPokemon,0);
        assertEquals(110,psychicPokemon.getHP());
        firePokemon.attack(waterPokemon,1);
        assertNotEquals(10,waterPokemon.getHP());
        assertEquals(30,waterPokemon.getHP());
    }

    @Test
    public void attackedByWaterPokemon() {
        waterPokemon.attack(firePokemon,0);
        assertNotEquals(25,firePokemon.getHP());
        assertEquals(0,firePokemon.getHP());
    }

}
package br.cefetmg.games.movement.behavior;

import br.cefetmg.games.movement.AlgoritmoMovimentacao;
import br.cefetmg.games.movement.Direcionamento;
import br.cefetmg.games.movement.Pose;
import com.badlogic.gdx.Input.Keys;
import java.util.Random;

/**
 * Movimenta o agente em uma direção aleatória, vagando pelo cenário.
 *
 * @author Flávio Coutinho <fegemo@cefetmg.br>
 */
public class Vagar extends AlgoritmoMovimentacao {

    private static final char NOME = 'w';
    private float maxAngular = 30f;
    protected Random rand;

    public Vagar() {
        super(NOME);
    }

    public Vagar(float tangencial, float angular) {
        super(NOME);
        maxVelocidade = tangencial;
        maxAngular = angular;
        this.rand = new Random();
    }

    @Override
    public Direcionamento guiar(Pose agente) {
        Direcionamento output = new Direcionamento();

        // calcula que direção tomar (configura um objeto Direcionamento 
        // e o retorna)
        // ...
        // super.alvo já contém a posição do alvo
        // agente (parâmetro) é a pose do agente que estamos guiando
        // ...
        output.velocidade = agente.getOrientacaoComoVetor().scl(maxVelocidade);
        output.rotacao = randomBinomial() * maxAngular;
        return output;
    }
    
    public float randomBinomial(){
        return rand.nextFloat() - rand.nextFloat();
    }

    @Override
    public int getTeclaParaAtivacao() {
        return Keys.W;
    }
}

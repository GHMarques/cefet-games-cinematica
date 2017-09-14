package br.cefetmg.games.movement.behavior;

import br.cefetmg.games.movement.AlgoritmoMovimentacao;
import br.cefetmg.games.movement.Direcionamento;
import br.cefetmg.games.movement.Pose;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Vector3;

/**
 * Guia o agente na direção do alvo.
 *
 * @author Flávio Coutinho <fegemo@cefetmg.br>
 */
public class Chegar extends AlgoritmoMovimentacao {

    private static final char NOME = 'c';
    private float time = (float) 0.25;
    private float radius = (float) 5;

    public Chegar(float maxVelocidade) {
        this(NOME, maxVelocidade);
    }

    protected Chegar(char nome, float maxVelocidade) {
        super(nome);
        this.maxVelocidade = maxVelocidade;
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
        Vector3 velocidade = new Vector3(super.alvo.getObjetivo().x - agente.posicao.x, super.alvo.getObjetivo().y - agente.posicao.y, super.alvo.getObjetivo().z - agente.posicao.z);
        output.velocidade = velocidade;
        if(output.velocidade.len() <= radius){
            return output;
        }
        output.velocidade = output.velocidade.scl(1/time);
        if(output.velocidade.len() >= this.maxVelocidade){
            output.velocidade.nor();
            output.velocidade = output.velocidade.scl(maxVelocidade);
        }
        
        
        agente.olharNaDirecaoDaVelocidade(output.velocidade);
        output.rotacao = 0;
        return output;
    }

    @Override
    public int getTeclaParaAtivacao() {
        return Keys.C;
    }
}

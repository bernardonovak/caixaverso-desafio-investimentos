package br.bnovak.caixaverso.desafio.investimentos.Entities;

import br.bnovak.caixaverso.desafio.investimentos.Enum.Perfil;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "CLIENTE")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(name = "PERFIL", nullable = false)
    private Perfil perfil;

    @Column(name = "PONTUACAO", nullable = false)
    private Integer pontuacao;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<Simulacao> simulacoes;

    public Cliente() {
    }

    public Cliente(Integer id) {
        this.id = id;
    }

    public Cliente(Perfil perfil, Integer pontuacao) {
        this.perfil = perfil;
        this.pontuacao = pontuacao;
    }

    public Cliente(Integer id, Perfil perfil, Integer pontuacao) {
        this.id = id;
        this.perfil = perfil;
        this.pontuacao = pontuacao;
    }

    public Cliente(Cliente original) {
        if(original != null){
            this.id = original.id;
            this.perfil = original.perfil;
            this.pontuacao = original.pontuacao;
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    public Integer getPontuacao() {
        return pontuacao;
    }

    public void setPontuacao(Integer pontuacao) {
        this.pontuacao = pontuacao;
    }
}

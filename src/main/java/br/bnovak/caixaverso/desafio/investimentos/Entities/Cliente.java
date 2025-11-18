package br.bnovak.caixaverso.desafio.investimentos.Entities;

import br.bnovak.caixaverso.desafio.investimentos.Enum.PerfilRisco;
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
    private PerfilRisco perfil;

    @Column(name = "PONTUACAO", nullable = false)
    private Integer pontuacao;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<Simulacao> simulacoes;

    public Cliente() {
    }

    public Cliente(Integer id) {
        this.id = id;
    }

    public Cliente(PerfilRisco perfil, Integer pontuacao) {
        this.perfil = perfil;
        this.pontuacao = pontuacao;
    }

    public Cliente(Integer id, PerfilRisco perfil, Integer pontuacao) {
        this.id = id;
        this.perfil = perfil;
        this.pontuacao = pontuacao;
    }

    public Cliente(Integer id, PerfilRisco perfil, Integer pontuacao, List<Simulacao> simulacoes) {
        this.id = id;
        this.perfil = perfil;
        this.pontuacao = pontuacao;
        this.simulacoes = simulacoes;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public PerfilRisco getPerfil() {
        return perfil;
    }

    public void setPerfil(PerfilRisco perfil) {
        this.perfil = perfil;
    }

    public Integer getPontuacao() {
        return pontuacao;
    }

    public void setPontuacao(Integer pontuacao) {
        this.pontuacao = pontuacao;
    }
}

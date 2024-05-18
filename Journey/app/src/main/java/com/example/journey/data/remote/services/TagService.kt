package com.example.journey.data.remote.services

import com.example.journey.data.models.Tag

class TagService {
    private val _tagList = listOf(
        Tag(0, "Transformar"),
        Tag(1, "Cuidar"),
        Tag(2, "Diversificar"),
        Tag(3, "Respeitar"),
        Tag(4, "Produzir bem"),
        Tag(5, "Criatividade"),
        Tag(6, "Inovação"),
        Tag(7, "Excelência"),
        Tag(8, "Reconhecimento"),
        Tag(9, "Surpreender"),
        Tag(10, "Novidade"),
        Tag(11, "Expansão"),
        Tag(12, "Manutenção"),
        Tag(13, "Agilidade"),
        Tag(14, "Comportamento"),
        Tag(15, "Transparência"),
        Tag(16, "Expectativas x Restrições"),
        Tag(17, "Desenvolvimento pessoal"),
        Tag(18, "Softskills"),
        Tag(19, "Aprendizado contínuo"),
        Tag(20, "Evolução"),
        Tag(21, "Mercado"),
        Tag(22, "Constância"),
        Tag(23, "Diálogo"),
        Tag(24, "Compartilhar")
    )

    fun listTags(): List<Tag> {
        return _tagList
    }
}
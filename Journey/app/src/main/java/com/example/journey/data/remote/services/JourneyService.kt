package com.example.journey.data.remote.services

import com.example.journey.data.models.Journey
import com.example.journey.data.models.Tag

class JourneyService {
    private var journeyList = mutableListOf(
        Journey(
            title = "Promova um momento de mentoria reversa.",
            description = "Você já pensou no que a nova geração pode ensinar para líderes que já " +
                    "estão no mercado há anos? \n" +
                    "\n" +
                    "Essa missão propõe uma inversão de papéis: desafiamos você, líder experiente, " +
                    "a abrir-se para novas perspectivas e aprendizados com seus colaboradores " +
                    "mais jovens e entender quais os impactos positivos eles podem trazer para o " +
                    "seu dia-a-dia e você, que acabou de entrar no mercado, comece a pensar em como " +
                    "sua carreira ainda em construção pode nos ajudar a crescer!",
            publisher = "Ana Carolina M.",
            superpower = "A Fabulosa Flecha da Agilidade",
            tags = listOf("Aprendizado contínuo", "Diálogo", "Compartilhar"),
            nuts = 200
        ),
        Journey(
            title = "Participe do Webinar da Zenklub sobre Saúde Mental.",
            description = "Em tempos desafiadores, a Zenklub está planejando um evento gratuito e " +
                    "super importante, uma oportunidade valiosa para explorarmos juntos estratégias " +
                    "e práticas para cuidar da nossa saúde mental e entender como promover o " +
                    "bem-estar de todos no dia-a-dia.\n" +
                    "\n" +
                    "Instruções dessa Missão:\n" +
                    "1) Registre-se: Garanta sua participação no link fornecido abaixo.\n" +
                    "2) Prepare-se: Reserve um tempo para se desconectar das distrações e se " +
                    "concentrar em cuidar de si.\n" +
                    "3) Participe: Conecte-se ao webinar no horário designado e esteja pronto(a) para " +
                    "absorver insights valiosos sobre saúde mental.\n" +
                    "4) Aplique o que Aprendeu: Após o webinar, comprometa-se a aplicar pelo menos uma " +
                    "estratégia aprendida em sua rotina diária.\n" +
                    "\n" +
                    "No mais, aproveite a experiência!\n" +
                    "\n" +
                    "Link de acesso: https://zenklub.com.br/site/webinar/mai24",
            publisher = "Mônica Araújo",
            superpower = "O Impenetrável Escudo do Cuidado",
            tags = listOf(
                "Desenvolvimento pessoal",
                "Comportamento",
                "Cuidar"
            ),
            nuts = 190
        ),
        Journey(
            title = "Promova um momento de mentoria reversa.",
            description = "Você já pensou no que a nova geração pode ensinar para líderes que já " +
                    "estão no mercado há anos? \n" +
                    "\n" +
                    "Essa missão propõe uma inversão de papéis: desafiamos você, líder experiente, " +
                    "a abrir-se para novas perspectivas e aprendizados com seus colaboradores " +
                    "mais jovens e entender quais os impactos positivos eles podem trazer para o " +
                    "seu dia-a-dia e você, que acabou de entrar no mercado, comece a pensar em como " +
                    "sua carreira ainda em construção pode nos ajudar a crescer!",
            publisher = "Ana Carolina M.",
            superpower = "A Fabulosa Flecha da Agilidade",
            tags = listOf("Aprendizado contínuo", "Diálogo", "Compartilhar"),
            nuts = 200
        ),
        Journey(
            title = "Promova um momento de mentoria reversa.",
            description = "Você já pensou no que a nova geração pode ensinar para líderes que já " +
                    "estão no mercado há anos? \n" +
                    "\n" +
                    "Essa missão propõe uma inversão de papéis: desafiamos você, líder experiente, " +
                    "a abrir-se para novas perspectivas e aprendizados com seus colaboradores " +
                    "mais jovens e entender quais os impactos positivos eles podem trazer para o " +
                    "seu dia-a-dia e você, que acabou de entrar no mercado, comece a pensar em como " +
                    "sua carreira ainda em construção pode nos ajudar a crescer!",
            publisher = "Ana Carolina M.",
            superpower = "A Fabulosa Flecha da Agilidade",
            tags = listOf("Aprendizado contínuo", "Diálogo", "Compartilhar"),
            nuts = 200
        )
    )
    fun getJourneysList(): List<Journey> {
        return journeyList
    }

    fun createJourney(journey: Journey) {
        journeyList += journey
    }
}
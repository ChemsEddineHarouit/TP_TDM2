package com.example.tdm2.enumerations

import java.util.*

enum class Wilaya: Comparable<Wilaya> {
    Tlemcen {
        override fun getMatricule() = 13
        override fun toString() = "Tlemcen"
    },
    Tizi_Ouzou {
        override fun getMatricule() = 15
        override fun toString() = "Tizi Ouzou"
    },
    Tissemsilt {
        override fun getMatricule() = 38
        override fun toString() = "Tissemsilt"
    },
    Tipaza {
        override fun getMatricule() = 42
        override fun toString() = "Tipaza"
    },
    Tindouf {
        override fun getMatricule() = 37
        override fun toString() = "Tindouf"
    },
    Tiaret {
        override fun getMatricule() = 14
        override fun toString() = "Tiaret"
    },
    Tebessa {
        override fun getMatricule() = 12
        override fun toString() = "Tébessa"
    },
    Tamanrasset {
        override fun getMatricule() = 11
        override fun toString() = "Tamanrasset"
    },
    Souk_Ahras {
        override fun getMatricule() = 41
        override fun toString() = "Souk Ahras"
    },
    Skikda {
        override fun getMatricule() = 21
        override fun toString() = "Skikda"
    },
    Sidi_Bel_Abbes {
        override fun getMatricule() = 22
        override fun toString() = "Sidi Bel Abbès"
    },
    Setif {
        override fun getMatricule() = 19
        override fun toString() = "Sétif"
    },
    Saida {
        override fun getMatricule() = 20
        override fun toString() = "Saïda"
    },
    Relizane {
        override fun getMatricule() = 48
        override fun toString() = "Relizane"
    },
    Naama {
        override fun getMatricule() = 45
        override fun toString() = "Naâma"
    },
    Mostaganem {
        override fun getMatricule() = 27
        override fun toString() = "Mostaganem"
    },
    Mila {
        override fun getMatricule() = 43
        override fun toString() = "Mila"
    },
    Medea {
        override fun getMatricule() = 26
        override fun toString() = "Médéa"
    },
    Mascara {
        override fun getMatricule() = 29
        override fun toString() = "Mascara"
    },
    MSila {
    override fun getMatricule() = 28
    override fun toString() = "M'Sila"
    },
    Laghouat {
        override fun getMatricule() = 3
        override fun toString() = "Laghouat"
    },
    Khenchela {
        override fun getMatricule() = 40
        override fun toString() = "Khenchela"
    },
    Jijel {
        override fun getMatricule() = 18
        override fun toString() = "Jijel"
    },
    Guelma {
        override fun getMatricule() = 24
        override fun toString() = "Guelma"
    },
    Ghardaia {
        override fun getMatricule() = 47
        override fun toString() = "Ghardaïa"
    },
    Djelfa {
        override fun getMatricule() = 17
        override fun toString() = "Djelfa"
    },
    Constantine {
        override fun getMatricule() = 25
        override fun toString() = "Constantine"
    },
    Chlef {
        override fun getMatricule() = 2
        override fun toString() = "Chlef"
    },
    Boumerdes {
        override fun getMatricule() = 35
        override fun toString() = "Boumerdès"
    },
    Bouira {
        override fun getMatricule() = 10
        override fun toString() = "Bouira"
    },
    Bordj_Bou_Arreridj {
        override fun getMatricule() = 34
        override fun toString() = "Bordj Bou Arreridj"
    },
    Blida {
        override fun getMatricule() = 9
        override fun toString() = "Blida"
    },
    Biskra {
        override fun getMatricule() = 7
        override fun toString() = "Biskra"
    },
    Bejaia {
        override fun getMatricule() = 6
        override fun toString() = "Béjaïa"
    },
    Bechar {
        override fun getMatricule() = 8
        override fun toString() = "Béchar"
    },
    Batna {
        override fun getMatricule() = 5
        override fun toString() = "Batna"
    },
    Oum_El_Bouaghi {
        override fun getMatricule() = 4
        override fun toString() = "Oum El Bouaghi"
    },
    Ouargla {
        override fun getMatricule() = 30
        override fun toString() = "Ouargla"
    },
    Oran {
        override fun getMatricule() = 31
        override fun toString() = "Oran"
    },
    Illizi {
        override fun getMatricule() = 33
        override fun toString() = "Illizi"
    },
    El_Tarf {
        override fun getMatricule() = 36
        override fun toString() = "El Tarf"
    },
    El_Oued {
        override fun getMatricule() = 39
        override fun toString() = "El Oued"
    },
    El_Bayadh {
        override fun getMatricule() = 32
        override fun toString() = "El Bayadh"
    },
    Annaba {
        override fun getMatricule() = 23
        override fun toString() = "Annaba"
    },
    Alger {
        override fun getMatricule() = 16
        override fun toString() = "Alger"
    },
    Ain_Temouchent {
        override fun getMatricule() = 46
        override fun toString() = "Aïn Témouchent"
    },
    Ain_Defla {
        override fun getMatricule() = 44
        override fun toString() = "Aïn Defla"
    },
    Adrar {
        override fun getMatricule() = 1
        override fun toString() = "Adrar"
    };
    abstract fun getMatricule() : Int

    companion object {
        //Creating a map for reverse lookup by getMatricule()
        private val map = values().associateBy(Wilaya::getMatricule)
        fun getFromMatricule(type: Int) = map[type]
    }

}


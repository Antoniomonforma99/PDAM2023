<script setup>

    import CategoriaService from '../../../services/CategoriaService';
    import { ref, onBeforeMount, onMounted } from 'vue';
    import { useToast } from "primevue/usetoast";
    import router from '../../../routes';

    const toast = useToast();

    const nombre = ref('');

    const categoriaService = new CategoriaService();


    async function createCategoria(){

        console.log(nombre.value)

        await categoriaService.create(nombre.value)
            .then((resp) => {
                console.log("respu" +JSON.stringify(resp))

                router.push('/categorias')

        })
    }

    onMounted(() => {
        
    })
</script>

<template>
    <div class="lg:col-12 card">
        <h5>NUEVA CATEGORÍA</h5>
        <div class="formgroup-inline">
            <div class="field">
                <label for="nombre" class="p-sr-only">Nombre</label>
                <InputText id="nombre" v-model="nombre" type="text" placeholder="Nombre de la categía" />
            </div>

                <Button @click="createCategoria()" label="Guardar"></Button>
            </div>
        </div>
</template>


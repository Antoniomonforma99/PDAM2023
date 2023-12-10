<script setup>

import ProductoService from '../../../services/ProductoService';
import { ref, onBeforeMount, onMounted } from 'vue';

const nombre = ref('');
const precio = ref(0);
const description = ref('');
const ingredientes = ref([]);
const categoriaSelected = ref ('');

</script>

<template>
    <div class="grid">
        <div class="col-12">
            <div class="card">
                <h5>NUEVO PRODUCTO</h5>
                <div class="p-fluid formgrid grid">
                    <div class="field col-12 md:col-6">
                        <label for="nombre">Nombre</label>
                        <InputText id="nombre" v-model="nombre" type="text" />
                    </div>
                    <div class="field col-12 md:col-6">
                        <label for="precio">Precio</label>
                        <InputText id="precio" v-model="precio" type="double" />
                    </div>
                    <div class="field col-12">
                        <label for="description">Descripción</label>
                        <Textarea id="description" v-model="description" rows="4" />
                    </div>
                    <div class="field col-12 md:col-6">
                        <label for="ingredientes">Ingredientes separados por coma</label>
                        <InputText id="ingredientes" v-model="ingredientes" type="text" />
                    </div>
                    <div class="field col-12 md:col-6">
                        <label for="state">Categoría</label>
                        <Dropdown id="state" v-model="categoriaSelected" :options="categorias" optionLabel="categoria" placeholder="Elija una"></Dropdown>
                    </div>
                    <div class="field col-12 md:col-12">
                        <label for="imagen">Imágenes</label>
                        <FileUpload  name="demo[]" @uploader="onUpload" :multiple="true" accept="image/*" :maxFileSize="1000000" customUpload />
                    </div>
                    

                </div>
            </div>
        </div>
    </div>

</template>

<style lang="scss" scoped>

</style>
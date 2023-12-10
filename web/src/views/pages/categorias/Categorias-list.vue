<script setup>
    import { ref, onBeforeMount, onMounted } from 'vue';
    import { FilterMatchMode, FilterOperator } from 'primevue/api';
    import router from '../../../routes';
    import CategoriaService from '../../../services/CategoriaService'

    const categoriasFilter = ref(null);
    const loadingCategories = ref(null);
    const categorias = ref([]);

    const categoriaService = new CategoriaService();

    async function getCategorias(){
        await categoriaService.getAll().then((resp) => {
            categorias.value = resp;
        })
    }

    const initCategoriasFilter = () => {
        categoriasFilter.value = {
            global: { value: null, matchMode: FilterMatchMode.CONTAINS },
            name: { value: null, matchMode: FilterMatchMode.CONTAINS }
        };
    };

    const clearBusinessFilter = () => {
        initBusinessFilter();
    }


    
    // function goToCategoriaDetails(categoriaId){
    //     router.push(
    //         {
    //             name : 'business-details',
    //             params : {categoriaId}
    //         }
    //     )   
    // }

    function goToNewCategoria() {
        router.push(
            {
                name : 'categoria-new'
            }
        )
    }

    onBeforeMount(() => {
        initCategoriasFilter();
    });

    onMounted(() => {
        getCategorias();

    })
</script>

<template>
    <div class="grid">
    <div class="xl:col-12 lg:col-12 md:col-12">
        <div class="card">
            <div style="display: flex; align-items: baseline; justify-content: space-between;">
                    <h4 class="ml-3">CATEGORÍAS ({{ categorias.length }})</h4>
                    <div style="display: flex;" class="ml-5">
                        <Button @click="goToNewCategoria()" label="Nueva categoría" outlined class="mr-5" />
                    </div>
                </div>
            <br>
            <DataTable :value="categorias" :paginator="true" class="p-datatable-gridlines" :rows="20" dataKey="id"
            v-model:filters="categoriasFilter" :rowHover="true" :loading="loadingCategories" responsiveLayout="scroll"
                :globalFilterFields="['id']">

                <template #header>
                    <div class="flex justify-content-between flex-column sm:flex-row">
                        <Button type="button" icon="pi pi-filter-slash" label="Limpiar filtros"
                            class="p-button-outlined mb-2" @click="clearBusinessFilter()" />
                        <span class="p-input-icon-left mb-2">
                            <i class="pi pi-search" />
                            <InputText v-model="categoriasFilter['global'].value" placeholder="Buscar..."
                                style="width: 100%" />
                        </span>
                    </div>
                </template>
                <template #empty> No se ha encontrado ninguna categoría. </template>
                <template #loading> Cargando categorías. Por favor, espere. </template>
                <Column field="businessName" header="Negocio" style="min-width: 12rem" sortable>
                    <template #body="{ data }">
                        <a class="pointer" @click="goToCategoriaDetails(data.id)" style="display: block; width: 100%; height: 100%;">
                            {{ data.nombre }}
                        </a> 
                    </template>
                    <template #filter="{ filterModel }">
                        <InputText type="text" v-model="filterModel.value" class="p-column-filter"
                            placeholder="Buscar por nombre de categoría" />
                    </template>
                </Column>
            </DataTable>
        </div>
    </div>
</div>
</template>

<style lang="scss" scoped>
    .p-button.p-button-icon-only.p-button-rounded {
        width: 2.5rem;
        height: 2.5rem;
    }
    .pointer{
        cursor: pointer;
    }
    .pointer:hover{
        color: #6366f1;
    }
</style>
<script setup>
import { FilterMatchMode, FilterOperator } from 'primevue/api';
import { ref, onBeforeMount, onMounted } from 'vue';
import ProductoService from '../../../services/ProductoService';
import router from '../../../routes';

const productosFilter = ref(null);
const productosLoading = ref(null);
const productos = ref([]);

const layout = ref('grid');

const productosService = new ProductoService();

async function getProductos() {
    await productosService.getAllProductos().then((resp) => {
        productos.value = resp;

    });
}

function goToNewProducto() {
        router.push(
            {
                name : 'producto-new'
            }
        )
}

function goToProductoDetail(idProducto) {
    console.log("CLICK");
        router.push(
            {
                name : 'producto-detail',
                params : {idProducto}
            }
        )
}

onMounted(() => {
    getProductos();
    
})

</script>

<template>
    <div class="grid">
        <div class="col-12">
            <div class="card">
                <div style="display: flex; align-items: baseline; justify-content: space-between;">
                    <h4 class="ml-3">PRODUCTOS ({{ productos.length }})</h4>
                    <div style="display: flex;" class="ml-5">
                        <Button @click="goToNewProducto()" label="Nuevo producto" outlined class="mr-5" />
                    </div>
                </div>
                <br>
                <DataView :value="productos" :layout="layout" :paginator="true" :rows="9" :sortOrder="sortOrder" :sortField="sortField">
                    <template #header>
                        <div class="grid grid-nogutter">
                            <div class="col-6 text-left">
                                <Dropdown v-model="sortKey" :options="sortOptions" optionLabel="label" placeholder="Sort By Price" @change="onSortChange($event)" />
                            </div>
                            <div class="col-6 text-right">
                                <DataViewLayoutOptions v-model="layout" />
                            </div>
                        </div>
                    </template>
                    <template #list="slotProps">
                        <div class="col-12">
                            <div class="flex flex-column md:flex-row align-items-center p-3 w-full">
                                <img :src="'demo/images/product/' + slotProps.data.nombre" :alt="slotProps.data.nombre" class="my-4 md:my-0 w-9 md:w-10rem shadow-2 mr-5" />
                                <div class="flex-1 text-center md:text-left">
                                    <div class="font-bold text-2xl"> {{ slotProps.data.nombre }}</div>
                                    <div class="mb-3">{{ slotProps.data.descripcion }}</div>
                                    <!-- <Rating :modelValue="slotProps.data.rating" :readonly="true" :cancel="false" class="mb-2"></Rating> -->
                                    <div class="flex align-items-center">
                                        <i class="pi pi-tag mr-2"></i>
                                        <span class="font-semibold">{{ slotProps.data.categoria.nombre }}</span>
                                    </div>
                                </div>
                                <div class="flex flex-row md:flex-column justify-content-between w-full md:w-auto align-items-center md:align-items-end mt-5 md:mt-0">
                                    <span class="text-2xl font-semibold mb-2 align-self-center md:align-self-end">${{ slotProps.data.precio }}</span>
                                    <!-- <Button icon="pi pi-shopping-cart" label="Add to Cart" :disabled="slotProps.data.inventoryStatus === 'OUTOFSTOCK'" class="mb-2"></Button>
                                    <span :class="'product-badge status-' + slotProps.data.inventoryStatus.toLowerCase()">{{ slotProps.data.inventoryStatus }}</span> -->
                                </div>
                            </div>
                        </div>
                    </template>

                    <template #grid="slotProps">
                        <div class="col-12 md:col-4">
                            <div class="card m-3 border-1 surface-border">
                                <div class="flex align-items-center justify-content-between">
                                    <div class="flex align-items-center">
                                        <i class="pi pi-tag mr-2"></i>
                                        <span class="font-semibold">{{ slotProps.data.categoria.nombre }}</span>
                                    </div>
                                    <!-- <span :class="'product-badge status-' + slotProps.data.inventoryStatus.toLowerCase()">{{ slotProps.data.inventoryStatus }}</span> -->
                                </div>
                                <div class="text-center">
                                    <img :src="'http://localhost:8080/download/' + slotProps.data.imgUrl" :alt="slotProps.data.nombre" class="w-9 shadow-2 my-3 mx-0" />
                                    <div @click="goToProductoDetail(slotProps.data.id)" class=" pointer text-2xl font-bold">{{ slotProps.data.nombre }}</div>
                                    <div class="mb-3">{{ slotProps.data.descripcion }}</div>
                                    <Rating :modelValue="slotProps.data.valoracionMedia" :readonly="true" :cancel="false"></Rating>
                                </div>
                                <div class="flex align-items-center justify-content-between">
                                    <span class="text-2xl font-semibold">${{ slotProps.data.precio }}</span>
                                    <!-- <Button icon="pi pi-shopping-cart" :disabled="slotProps.data.inventoryStatus === 'OUTOFSTOCK'"></Button> -->
                                </div>
                            </div>
                        </div>
                    </template>
                </DataView>
            </div>
        </div>
    </div>
</template>

<style lang="scss" scoped>
    .pointer {
        cursor : pointer;
    }
</style>
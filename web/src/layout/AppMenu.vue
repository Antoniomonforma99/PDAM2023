<script setup>
import { ref } from 'vue';

import AppMenuItem from '@/layout/AppMenuItem.vue';

const model = ref([
    {
        label: 'Inicio',
        items: [
            { label: 'DASHBOARD', icon: 'pi pi-fw pi-home', to: '/dashboard' },
            // { label: 'Posición global', icon: 'pi pi-fw pi-globe', to: '/' }
        ]
    },
    {
        label: 'GESTIÓN DE PRODUCTOS',
        items: [   
                    {
                        label: 'PRODUCTOS',
                        to: '/productos'
                    },
                    {
                        label: 'MENUS',
                        to: '/menus'
                    },
        ]
    },
    {
        label: 'GESTIÓN DE PERSONAL',
        items: [
                    {
                        label: 'VER PERSONAL',
                        to: '/personal'
                    }
        ]
    }

]);
</script>

<template>
    <ul class="layout-menu">
        <template v-for="(item, i) in model" :key="item">
            <app-menu-item v-if="!item.separator" :item="item" :index="i"></app-menu-item>
            <li v-if="item.separator" class="menu-separator"></li>
        </template>
    </ul>
</template>

<style lang="scss" scoped></style>

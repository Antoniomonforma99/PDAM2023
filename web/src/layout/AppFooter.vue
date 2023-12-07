<script setup>
import { useLayout } from '@/layout/composables/layout';
import { computed } from 'vue';

const { layoutConfig } = useLayout();

const logoUrl = computed(() => {
    if (layoutConfig.darkTheme.value) {
        return '/admin/layout/images/'
    } else {
        return '/admin/layout/images/'
    }
});
</script>

<template>
    <div class="layout-footer">
        <img :src="logoUrl" alt="Logo" height="10" class="mr-2" style="margin-left: .5rem;" />
    </div>
</template>
<style lang="scss" scoped></style>
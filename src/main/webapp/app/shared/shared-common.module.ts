import { NgModule } from '@angular/core';

import { VirtaSharedLibsModule, JhiAlertComponent, JhiAlertErrorComponent } from './';

@NgModule({
    imports: [VirtaSharedLibsModule],
    declarations: [JhiAlertComponent, JhiAlertErrorComponent],
    exports: [VirtaSharedLibsModule, JhiAlertComponent, JhiAlertErrorComponent]
})
export class VirtaSharedCommonModule {}

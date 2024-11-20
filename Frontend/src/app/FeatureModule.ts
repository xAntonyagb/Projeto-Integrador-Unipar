import { NgModule } from "@angular/core";
import { FeatureImportsModule } from "./FeatureImportsModule";

@NgModule({
    imports: [FeatureImportsModule],
    exports: [FeatureImportsModule],
}) export class FeatureModule {}

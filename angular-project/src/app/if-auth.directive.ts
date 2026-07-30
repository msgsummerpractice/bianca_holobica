import { Directive, effect, input, inject, ViewContainerRef, TemplateRef } from '@angular/core';

@Directive({
  selector: '[ifAuth]',
  standalone: true,
})
export class IfAuthDirective {
  private readonly _viewContainerRef = inject(ViewContainerRef);
  private readonly _templateRef = inject(TemplateRef);

  ifAuth = input<boolean>(true);

  constructor() {
    effect(() => {
      if (this.ifAuth()) {
        this._viewContainerRef.createEmbeddedView(this._templateRef);
        console.log('User is authenticated. Displaying content.');
      } else {
        this._viewContainerRef.clear();
        console.log('User is not authenticated. Hiding content.');
      }
    });
  }
}

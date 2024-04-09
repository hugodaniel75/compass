import { WebPlugin } from '@capacitor/core';

import type { CompassPlugin } from './definitions';

export class CompassWeb extends WebPlugin implements CompassPlugin {
  
  async echo(options: { value: string }): Promise<{ value: string }> {
    console.log('ECHO', options);
    return options;
  }

  check(): boolean {
    // Verifica si el navegador es compatible con el evento deviceorientationabsolute
    if ('DeviceOrientationEvent' in window && 'AbsoluteOrientationSensor' in window) {
      // El navegador soporta el evento deviceorientationabsolute
      console.log('El navegador soporta el evento deviceorientationabsolute.');
      return true;
    } else {
      // El navegador no soporta el evento deviceorientationabsolute
      console.log('El navegador no soporta el evento deviceorientationabsolute.');
      return false;
    }
  }

  /**
   * Empezar a obtener los datos de orientación del dispositivo
   */
  async start(): Promise<void> {
    // logic here
    console.log('Start compass');

    // Si es una aplicación híbrida (ej. en un dispositivo móvil)
    window.addEventListener('deviceorientationabsolute', this.getOrientation);
  }

  /**
   * Detener 
   */
  stop(): void {
    window.removeEventListener('deviceorientationabsolute', this.getOrientation);
  }


  getOrientation = (event: any) => {
    window.dispatchEvent(new CustomEvent('headingUpdate', { detail: event }));
  }
}

import { WebPlugin } from '@capacitor/core';

import type { TruvideoSdkImagePlugin } from './definitions';

export class TruvideoSdkImageWeb extends WebPlugin implements TruvideoSdkImagePlugin {
  async echo(options: { value: string }): Promise<{ value: string }> {
    console.log('ECHO', options);
    return options;
  }
}

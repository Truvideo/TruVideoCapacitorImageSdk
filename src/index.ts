import { registerPlugin } from '@capacitor/core';

import type { TruvideoSdkImagePlugin } from './definitions';

const TruvideoSdkImage = registerPlugin<TruvideoSdkImagePlugin>('TruvideoSdkImage', {
  web: () => import('./web').then((m) => new m.TruvideoSdkImageWeb()),
});

export * from './definitions';
export { TruvideoSdkImage };

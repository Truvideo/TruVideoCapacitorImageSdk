import { registerPlugin } from '@capacitor/core';

import type { TruvideoSdkImagePlugin } from './definitions';

const TruvideoSdkImage = registerPlugin<TruvideoSdkImagePlugin>('TruvideoSdkImage');

export * from './definitions';
export { TruvideoSdkImage };

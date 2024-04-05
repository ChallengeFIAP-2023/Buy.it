import AsyncStorage from '@react-native-async-storage/async-storage';

// Storage import
import { AUTH_TOKEN_STORAGE } from '@storage/storageConfig';

type StorageAuthTokenProps = {
  token: string;
};

export async function storageAuthTokenSave({ token }: StorageAuthTokenProps) {
  await AsyncStorage.setItem(AUTH_TOKEN_STORAGE, JSON.stringify({ token }));
}

export async function storageAuthTokenGet() {
  const response = await AsyncStorage.getItem(AUTH_TOKEN_STORAGE);

  const { token }: StorageAuthTokenProps = response
    ? JSON.parse(response)
    : ({} as StorageAuthTokenProps);

  return token;
}

export async function removeAuthToken() {
  await AsyncStorage.removeItem(AUTH_TOKEN_STORAGE);
}

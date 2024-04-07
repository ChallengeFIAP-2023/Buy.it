import React from 'react';
import { Image as ImageIcon } from 'phosphor-react-native';
import { ImageSourcePropType } from 'react-native';
import * as ImagePicker from 'expo-image-picker';

// Theme import
import theme from '@theme/index';

// Style import
import { Container, Image, IconPic } from './styles';

interface Props {
  handleSetAvatar?: (uri: string) => void;
  imageSource: ImageSourcePropType;
  size: 'SM' | 'MD' | 'XXL';
}

export function UserAvatar({
  handleSetAvatar,
  imageSource,
  size = 'XXL',
}: Props) {
  const iconSize = size === 'XXL' ? theme.FONT_SIZE.XXL : theme.FONT_SIZE.LG;

  async function handleSelectImage() {
    const result = await ImagePicker.launchImageLibraryAsync({
      mediaTypes: ImagePicker.MediaTypeOptions.Images,
      allowsEditing: true,
      aspect: [5, 5],
      quality: 1,
      base64: true,
    });

    if (!result.canceled && handleSetAvatar) {
      handleSetAvatar(result.assets[0].uri);
    }
  }

  return (
    <Container
      {...(handleSetAvatar && {
        onPress: handleSelectImage,
      })}
      activeOpacity={0.7}
    >
      <Image source={imageSource} size={size} />

      {handleSetAvatar && (
        <IconPic size={size}>
          <ImageIcon size={iconSize} color={theme.COLORS.WHITE} weight="bold" />
        </IconPic>
      )}
    </Container>
  );
}

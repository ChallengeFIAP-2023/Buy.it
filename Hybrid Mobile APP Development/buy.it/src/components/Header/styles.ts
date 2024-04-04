import { ArrowLeft } from 'phosphor-react-native';
import styled from 'styled-components/native';

// Asset import
import LogoImage from '@assets/logo.png';

export const Container = styled.View`
  width: 100%;

  flex-direction: row;
  align-items: center;
  justify-content: center;
`;

export const Logo = styled.Image.attrs({
  source: LogoImage,
})`
  width: 80px;
  height: 80px;
`;

export const BackButton = styled.TouchableOpacity`
  flex: 1;
`;

export const Empty = styled.View`
  flex: 1;
`;

export const BackIcon = styled(ArrowLeft).attrs(({ theme }) => ({
  size: theme.FONT_SIZE.XXL,
  color: theme.COLORS.GRAY_300,
}))``;

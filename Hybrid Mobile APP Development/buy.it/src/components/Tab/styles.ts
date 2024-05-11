import { Text } from 'react-native';
import styled from 'styled-components/native';

export const TabText = styled(Text)`
  margin-left: 18px;
  margin-bottom: 2px;

  font-size: ${({ theme }) => theme.FONT_SIZE.SM}px;
  font-family: ${({ theme }) => theme.FONT_FAMILY.RALEWAY.SEMIBOLD};
  color: ${({ theme }) => theme.COLORS.WHITE};
`;

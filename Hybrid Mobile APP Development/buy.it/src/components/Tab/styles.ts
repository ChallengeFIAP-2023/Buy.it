import { Text } from 'react-native';
import styled from 'styled-components/native';

export const TabText = styled(Text)`
  margin-top: -10px;
  margin-bottom: 5px;

  font-size: ${({ theme }) => theme.FONT_SIZE.LG}px;
  font-family: ${({ theme }) => theme.FONT_FAMILY.RALEWAY.SEMIBOLD};
  color: ${({ theme }) => theme.COLORS.WHITE};
`;

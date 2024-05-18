import styled from 'styled-components/native';

export const Label = styled.Text`
  font-size: ${({ theme }) => theme.FONT_SIZE.LG}px;
  margin-bottom: 5px;
  font-family: ${({ theme }) => theme.FONT_FAMILY.RALEWAY.SEMIBOLD};
  color: ${({ theme }) => theme.COLORS.WHITE};
`;

export const StarButton = styled.TouchableOpacity`
  font-size: ${({ theme }) => theme.FONT_SIZE.XXL}px;
`;

export const StarsContainer = styled.View`
  margin-bottom: 15px;
`;
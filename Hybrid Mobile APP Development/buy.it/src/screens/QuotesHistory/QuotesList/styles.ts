import styled from 'styled-components/native';

export const QuotesWrapper = styled.View`
  padding-bottom: 50px;
`;

export const TextIndicator = styled.Text`
  font-size: ${({ theme }) => theme.FONT_SIZE.MD}px;
  font-family: ${({ theme}) => theme.FONT_FAMILY.RALEWAY.SEMIBOLD};
  color: ${({ theme}) => theme.COLORS.GRAY_300};
  margin-top: 50px;
  text-align: center;
`;
